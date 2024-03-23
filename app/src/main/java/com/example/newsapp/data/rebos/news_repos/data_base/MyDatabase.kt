package com.example.newsapp.data.rebos.news_repos.data_base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.api.model.Source
import com.example.newsapp.data.rebos.news_repos.data_base.daos.SourcesDao

@Database(entities = [Source::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun getSourcesDao(): SourcesDao
//    abstract fun getArticlesDao(): ArticlesDao

    companion object {
        private var myDataBase: MyDatabase? = null

        fun init(context: Context) {
            if (myDataBase == null) {
                myDataBase = Room.databaseBuilder(context, MyDatabase::class.java, "My_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }

        fun getInstance(): MyDatabase {
            return myDataBase!!
        }
    }

}