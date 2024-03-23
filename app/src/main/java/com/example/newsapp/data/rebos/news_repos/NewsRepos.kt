package com.example.newsapp.data.rebos.news_repos

import com.example.newsapp.api.model.Article
import com.example.newsapp.api.model.Source

interface NewsRepos {

    suspend fun loadSources(apiKey: String, categoryId: String ): List<Source?>

    suspend fun loadArticle(apiKey: String, sourceId: String = "", query: String): List<Article?>
}