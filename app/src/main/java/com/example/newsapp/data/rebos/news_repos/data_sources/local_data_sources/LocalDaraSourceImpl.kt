package com.example.newsapp.data.rebos.news_repos.data_sources.local_data_sources

import com.example.newsapp.api.model.Article
import com.example.newsapp.api.model.Source
import com.example.newsapp.data.rebos.news_repos.data_base.MyDatabase

class LocalDaraSourceImpl(private val database: MyDatabase): LocalDataSource {

    override suspend fun loadSources(categoryId: String): List<Source> {
        return database.getSourcesDao().getSources(categoryId)
    }

    override suspend fun loadArticle(sourceId: String): List<Article> {
        return listOf()
    }

    override suspend fun saveSources(sourcesList: List<Source?>) {
        val nonNullableList = sourcesList.filter {
            return@filter it != null
        } as List<Source>
        database.getSourcesDao().addSources(nonNullableList)
    }

    override suspend fun deleteSources(categoryId: String) {
        database.getSourcesDao().deleteSources(categoryId)
    }

}