package com.example.newsapp

import android.app.Application
import com.example.newsapp.data.rebos.news_repos.ConnectivityChecker
import com.example.newsapp.data.rebos.news_repos.data_base.MyDatabase

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ConnectivityChecker.context = this
        MyDatabase.init(this)
    }
}