package com.example.newsapp.ui.fragments.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.api.model.Article
import com.example.newsapp.databinding.ItemNewsBinding

class ArticlesAdapter(
    private var articles: List<Article? >):
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article? = articles[position]
        holder.binding.root.setOnClickListener{ onArticleClickListener?.onClick(article )
        }
        holder.binding.apply {
            newsSourceTv.text = article?.source?.name
            newsTitleTv.text = article?.title
            newsDateTv.text = article?.publishedAt
            Glide.with(holder.itemView)
                .load(article?.urlToImage)
                .into(holder.binding.newsImage)
        }
        holder.itemView.setOnClickListener {
            onArticleClickListener?.onClick(article)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList: List<Article?>){
        articles = newList
        notifyDataSetChanged()
    }

     interface OnArticleClickListener{
        fun onClick(article: Article?)
    }
     private var onArticleClickListener: OnArticleClickListener? = null

    fun setOnArticleClickListener(listener: OnArticleClickListener){
        onArticleClickListener = listener
    }
}