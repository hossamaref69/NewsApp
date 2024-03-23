package com.example.newsapp.model

import com.example.newsapp.R

data class Category (
    val id: String,
    val imageId: Int,
    val backgroundColorId: Int,
    val title: String
){
    companion object{
        val categoriesList = listOf (
            Category("sports", R.drawable.sports,R.color.red, "Sports"),
            Category("entertainment", R.drawable.politics,R.color.blue, "Entertainment"),
            Category("health", R.drawable.health,R.color.pink, "Health"),
            Category("business", R.drawable.bussines,R.color.brown, "Business"),
            Category("technology", R.drawable.technology,R.color.baby_blue, "Technology"),
            Category("science", R.drawable.science,R.color.yellow, "Science")
        )
    }
}
