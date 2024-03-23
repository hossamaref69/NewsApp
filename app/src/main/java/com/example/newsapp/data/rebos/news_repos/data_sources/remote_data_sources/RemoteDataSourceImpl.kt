package com.example.newsapp.data.rebos.news_repos.data_sources.remote_data_sources

import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.Article
import com.example.newsapp.api.model.ArticlesResponse
import com.example.newsapp.api.model.SourcesResponse
import kotlinx.coroutines.launch
import retrofit2.Call

class RemoteDataSourceImpl: RemoteDataSource {

    override suspend fun loadSources(apiKey: String, categoryId: String ): SourcesResponse {
       return ApiManager.getWebServices().getSources(apiKey, categoryId)
    }

    override suspend fun loadArticle(apiKey: String, sourceId: String, query: String): ArticlesResponse {
       return ApiManager.getWebServices().getArticles(apiKey, sourceId, query)

    }

    override suspend fun saveArticle(article: List<Article?>) {

    }

}