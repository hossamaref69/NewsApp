package com.example.newsapp.ui.search_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener

import com.example.newsapp.databinding.ActivitySearchBinding

import com.example.newsapp.ui.fragments.news.ArticlesAdapter

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private var adapter: ArticlesAdapter = ArticlesAdapter(listOf())
    val viewModel: SearchActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewSearch.adapter = adapter
        initListeners()
        binding.searchEt.addTextChangedListener {
            Log.d("tt", it.toString())
            if (it.toString().isNotBlank()) {
                viewModel.loadArticles(binding.searchEt.text.toString())
            }
            observeToLiveData()
        }
    }

    private fun observeToLiveData() {
        viewModel.isLoadingLiveData.observe(this) {
            changeProgressVisibility(it)
        }
        viewModel.errorMessageLiveData.observe(this) {
            if (it!!.isEmpty()) {
                changeErrorVisibility(false)
            } else {
                changeErrorVisibility(true, it)
            }
        }
        viewModel.articlesLiveData.observe(this) {
            adapter.update(it!!)
        }
    }

    private fun initListeners() {
        binding.closeSearch.setOnClickListener {
            binding.searchEt.text.clear()
        }

    }

    private fun changeErrorVisibility(isVisible: Boolean, message: String = "") {
        binding.errorView.root.isVisible = isVisible
        if (isVisible) {
            binding.errorView.errorTv.text = message
        }
    }

    private fun changeProgressVisibility(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible

    }

}