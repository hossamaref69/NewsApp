package com.example.newsapp.data.rebos.news_repos.data_sources.local_data_sources

import com.example.newsapp.api.model.Article
import com.example.newsapp.api.model.Source

interface LocalDataSource {

    suspend fun loadSources(categoryId: String): List<Source>

    suspend fun loadArticle(sourceId: String): List<Article>

    suspend fun saveSources (sourcesList: List<Source?>)

    suspend fun deleteSources (categoryId: String)

}