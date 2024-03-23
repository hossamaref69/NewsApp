package com.example.newsapp.data.rebos.news_repos

import android.util.Log
import com.example.newsapp.api.model.Article
import com.example.newsapp.api.model.Source
import com.example.newsapp.data.rebos.news_repos.data_sources.local_data_sources.LocalDataSource
import com.example.newsapp.data.rebos.news_repos.data_sources.remote_data_sources.RemoteDataSource

class NewsRepoImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : NewsRepos {

    override suspend fun loadSources(apiKey: String, categoryId: String): List<Source?> {
         if (ConnectivityChecker.isNetworkAvailable()) {
             localDataSource.deleteSources(categoryId)
            val response = remoteDataSource.loadSources(apiKey, categoryId)
             response.sources?.let {
                 localDataSource.saveSources(it)
             }
             return response.sources!!
        } else {
             return localDataSource.loadSources(categoryId)
        }
    }

    override suspend fun loadArticle(
        apiKey: String,
        sourceId: String,
        query: String,
    ): List<Article?> {
        return if (ConnectivityChecker.isNetworkAvailable()) {
            val response = remoteDataSource.loadArticle(apiKey, sourceId, query)
            response.articles!!
        } else {
            localDataSource.loadArticle(sourceId)
        }
    }

}