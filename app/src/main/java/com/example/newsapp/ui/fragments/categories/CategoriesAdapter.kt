package com.example.newsapp.ui.fragments.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.newsapp.databinding.ItemCategoryBinding
import com.example.newsapp.model.Category

class CategoriesAdapter(
    private var categories: List<Category>,
    val onCategoryClick: (category: Category)-> Unit )
    : Adapter<CategoriesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(categories[position])
    }


   inner class MyViewHolder(private val itemBinding: ItemCategoryBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(category: Category) {
            itemBinding.itemCategoryNameTv.text = category.title
            itemBinding.itemCategoryImage.setImageResource(category.imageId)
            itemBinding.itemCategoryCardView.setCardBackgroundColor(ContextCompat.getColor(
                    itemBinding.root.context,
                    category.backgroundColorId
                ))
            itemBinding.root.setOnClickListener{
                onCategoryClick.invoke(category)
            }
        }
    }
}