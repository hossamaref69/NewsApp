package com.example.newsapp.ui.search_activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.Article
import com.example.newsapp.api.model.Source
import com.example.newsapp.data.rebos.news_repos.NewsRepoImpl
import com.example.newsapp.data.rebos.news_repos.NewsRepos
import com.example.newsapp.data.rebos.news_repos.data_base.MyDatabase
import com.example.newsapp.data.rebos.news_repos.data_sources.local_data_sources.LocalDaraSourceImpl
import com.example.newsapp.data.rebos.news_repos.data_sources.remote_data_sources.RemoteDataSourceImpl
import kotlinx.coroutines.launch

class SearchActivityViewModel : ViewModel() {
    private val newsRepos: NewsRepos =
        NewsRepoImpl(RemoteDataSourceImpl(), LocalDaraSourceImpl(MyDatabase.getInstance()))
    val articlesLiveData: MutableLiveData<List<Article?>> = MutableLiveData()
    val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    var errorMessageLiveData: MutableLiveData<String?> = MutableLiveData(null)

    fun loadArticles(text: String) {
        isLoadingLiveData.value = true
        errorMessageLiveData.value = ""

        viewModelScope.launch {
            try {
                val articlesList =
                    newsRepos.loadArticle(apiKey = ApiManager.apiKey, query = text)
                isLoadingLiveData.value = false
                articlesLiveData.value = articlesList
                Log.d("tt", "${articlesList.size}")
            } catch (e: Exception) {
                isLoadingLiveData.value = false
                errorMessageLiveData.value = e.localizedMessage ?: "Try again later"
                Log.d("tt", "${e.localizedMessage} ${e.cause}")
            }
        }
    }
}