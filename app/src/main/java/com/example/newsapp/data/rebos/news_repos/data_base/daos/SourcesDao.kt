package com.example.newsapp.data.rebos.news_repos.data_base.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp.api.model.Article
import com.example.newsapp.api.model.Source
import com.example.newsapp.model.Category

@Dao
interface SourcesDao {

    @Insert
    fun addSources(source: List<Source>)

    @Query("DELETE FROM Source WHERE category = :category")
    fun deleteSources(category: String)

    @Query("SELECT * FROM Source WHERE category = :category")
    fun getSources(category: String): List<Source>


}