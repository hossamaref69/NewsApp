package com.example.newsapp.ui.fragments.article

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.newsapp.Constants
import com.example.newsapp.R
import com.example.newsapp.api.model.Article
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.ui.fragments.news.ArticlesAdapter
import kotlin.math.log

class ArticleFragment(val article: Article?): Fragment() {
    lateinit var binding: FragmentArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newsSource.text = article?.source?.name
        binding.newsTitle.text = article?.title
        binding.articleTv.text = article?.content
        Glide.with(this@ArticleFragment)
            .load(article?.urlToImage)
            .into(binding.imageView2)
        binding.newsTimeTv.text = article?.publishedAt
        viewAllArticle(article)
    }

   /* private fun bindArticle(article: Article) {
        binding.newsSource.text = article.source?.name
        binding.newsTitle.text = article.title
        binding.articleTv.text = article.content
        Glide.with(this@ArticleFragment)
            .load(article.urlToImage)
            .into(binding.imageView2)
        binding.newsTimeTv.text = article.publishedAt
        viewAllArticle(article)
    }*/

    private fun viewAllArticle(article: Article?) {
        binding.viewAllArticleTv.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article?.url))
            startActivity(intent)
        }
    }

//    private fun getParcelable(argument: Bundle?): Article {
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            argument?.getParcelable(Constants.PASSED_ARTICLE, Article::class.java) ?: Article()
//        } else {
//            @Suppress("DEPRECATION")
//            argument?.getParcelable(Constants.PASSED_ARTICLE) ?: Article()
//        }
//    }
}


