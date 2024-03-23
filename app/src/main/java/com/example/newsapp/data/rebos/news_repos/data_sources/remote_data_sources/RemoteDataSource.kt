package com.example.newsapp.data.rebos.news_repos.data_sources.remote_data_sources

import com.example.newsapp.api.model.Article
import com.example.newsapp.api.model.ArticlesResponse
import com.example.newsapp.api.model.Source
import com.example.newsapp.api.model.SourcesResponse

interface RemoteDataSource {

    suspend fun loadSources(apiKey: String, categoryId: String ): SourcesResponse

    suspend fun loadArticle(apiKey: String, sourceId: String, query: String = ""): ArticlesResponse

    suspend fun saveArticle(article: List<Article?>)
}