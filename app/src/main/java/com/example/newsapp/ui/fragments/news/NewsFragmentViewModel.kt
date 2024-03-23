package com.example.newsapp.ui.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.Article
import com.example.newsapp.api.model.ArticlesResponse
import com.example.newsapp.api.model.Source
import com.example.newsapp.api.model.SourcesResponse
import com.example.newsapp.data.rebos.news_repos.NewsRepoImpl
import com.example.newsapp.data.rebos.news_repos.NewsRepos
import com.example.newsapp.data.rebos.news_repos.data_base.MyDatabase
import com.example.newsapp.data.rebos.news_repos.data_sources.local_data_sources.LocalDaraSourceImpl
import com.example.newsapp.data.rebos.news_repos.data_sources.remote_data_sources.RemoteDataSourceImpl
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragmentViewModel: ViewModel() {
    val newsRepo: NewsRepos = NewsRepoImpl(RemoteDataSourceImpl(), LocalDaraSourceImpl(MyDatabase.getInstance()))
    val sourcesListLiveData: MutableLiveData<List<Source?>?> = MutableLiveData()
    val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    var errorMessageLiveData: MutableLiveData<String?> = MutableLiveData(null)
    val articlesLiveData: MutableLiveData<List<Article?>?> = MutableLiveData()

    fun loadSources(categoryId: String) {
        //changeProgressBarVisibility(true)
        isLoadingLiveData.value = true
        //changeErrorVisibility(false)
        errorMessageLiveData.value = ""

        viewModelScope.launch {
            try {
                val sourcesList = newsRepo.loadSources(ApiManager.apiKey, categoryId)
                isLoadingLiveData.value = false
                sourcesListLiveData.value = sourcesList
            }catch (e: Exception){
                isLoadingLiveData.value = false
                errorMessageLiveData.value = e.localizedMessage ?: "Try again later"
            }
        }

/*        ApiManager.getWebServices().getSources(ApiManager.apiKey, categoryId)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>,
                ) {
                    //changeProgressBarVisibility(false)
                    isLoadingLiveData.value = false
                    if (response.isSuccessful) {
                        response.body()?.sources.let {
                            //showTabs(it!!)
                            sourcesListLiveData.value = it!!
                        }

                    } else {

                        val errorBodyString = response.errorBody()?.string()
                        val response =
                            Gson().fromJson(errorBodyString, ArticlesResponse::class.java)
                        val errorMessage = if (response?.message != null) {
                            response.message
                        } else {
                            "Something went wrong, please try again later"
                        }
                        errorMessageLiveData.value = errorMessage
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    //changeProgressBarVisibility(false)
                    isLoadingLiveData.value = false
                    //changeErrorVisibility(true, t.localizedMessage ?: "Something went wrong please try again later" )
                    errorMessageLiveData.value = t.localizedMessage?: "Something went wrong please try again later"
                }
            })*/
    }

    fun loadArticles(sourceId: String) {
        isLoadingLiveData.value = true
        errorMessageLiveData.value = ""

        viewModelScope.launch {
            try {
                val articlesList =
                    newsRepo.loadArticle(ApiManager.apiKey, sourceId, query = "")
                isLoadingLiveData.value = false
                articlesLiveData.value = articlesList
            }catch (e: Exception){
                isLoadingLiveData.value = false
                errorMessageLiveData.value = e.localizedMessage ?: "Try again later"
            }
        }

       /* ApiManager.getWebServices().getArticles(ApiManager.apiKey, sourceId)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>,
                ) {
                    if (response.isSuccessful && response.body()?.articles?.isNotEmpty() == true) {
                        //adapter.update(response.body()?.articles!!)
                        articlesLiveData.value = response.body()?.articles
                    } else {

                        val errorBodyString = response.errorBody()?.string()
                        val response =
                            Gson().fromJson(errorBodyString, ArticlesResponse::class.java)
                        val errorMessage = if (response?.message != null) {
                            response.message
                        } else {
                            "Something went wrong, please try again later"
                        }
                        errorMessageLiveData.value = errorMessage

                    }
                }

                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    //changeProgressBarVisibility(false)
                    isLoadingLiveData.value = false
                    //changeErrorVisibility(true, t.localizedMessage ?: "Something went wrong please try again later" )
                    errorMessageLiveData.value = t.localizedMessage?: "Something went wrong please try again later"
                }
            })*/
    }
}