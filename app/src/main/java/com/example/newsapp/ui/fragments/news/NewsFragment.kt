package com.example.newsapp.ui.fragments.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.Constants
import com.example.newsapp.R
import com.example.newsapp.api.model.Article
import com.example.newsapp.api.model.Source
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.ui.fragments.article.ArticleFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class NewsFragment(private val categoryId: String) : Fragment(), OnTabSelectedListener {
    lateinit var binding: FragmentNewsBinding
    lateinit var viewModel: NewsFragmentViewModel
    private var adapter: ArticlesAdapter = ArticlesAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = ViewModelProvider(this)[NewsFragmentViewModel::class.java]
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadSources(categoryId)
        initListeners()
        binding.articlesRecyclerView.adapter = adapter
        observeToLiveData()

    }

    private fun observeToLiveData() {
        viewModel.sourcesListLiveData.observe(viewLifecycleOwner) {
            showTabs(it!!)
        }
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            changeLoaderVisibility(it)
        }
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            if (it!!.isEmpty()) {
                changeErrorVisibility(false)
            } else {
                changeErrorVisibility(true, it)
            }
        }
        viewModel.articlesLiveData.observe(viewLifecycleOwner) {
            adapter.update(it!!)
        }
    }

    private fun initListeners() {
        binding.errorView.retryBtn.setOnClickListener {
            viewModel.loadSources(categoryId)
        }
        binding.tabLayout.addOnTabSelectedListener(this)

        adapter.setOnArticleClickListener(object : ArticlesAdapter.OnArticleClickListener {
            override fun onClick(article: Article?) {
                /*  val argument = Bundle()
                  argument.putParcelable(Constants.PASSED_ARTICLE, article)*/
                //val articleFragment = ArticleFragment(article)
                //articleFragment.arguments = argument

                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, ArticleFragment(article))
                    .addToBackStack("")
                    .commit()
            }

        })
    }

    private fun showTabs(sources: List<Source?>) {
        sources.forEach { source ->
            val tab = binding.tabLayout.newTab()
            tab.text = source?.name
            binding.tabLayout.addTab(tab)
            tab.tag = source
        }
        binding.tabLayout.getTabAt(0)?.select()
    }

    private fun changeErrorVisibility(isVisible: Boolean, message: String = "") {
        binding.errorView.root.isVisible = isVisible
        if (isVisible) {
            binding.errorView.errorTv.text = message
        }
    }

    private fun changeLoaderVisibility(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val source = tab?.tag as Source?
        source?.id?.let {
            //loadArticles(it)
            viewModel.loadArticles(it)
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        val source = tab?.tag as Source?
        source?.id?.let {
            //loadArticles(it)
            viewModel.loadArticles(it)
        }
    }
}
