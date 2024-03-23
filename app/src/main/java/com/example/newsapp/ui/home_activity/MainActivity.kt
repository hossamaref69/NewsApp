package com.example.newsapp.ui.home_activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.ui.fragments.article.ArticleFragment
import com.example.newsapp.ui.fragments.categories.CategoriesFragment
import com.example.newsapp.ui.fragments.news.NewsFragment
import com.example.newsapp.ui.fragments.settings.SettingsFragment
import com.example.newsapp.ui.search_activity.SearchActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoriesFragment: CategoriesFragment

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFragments()
        startCategoriesFragment()
        initListeners()

    }

    private fun initListeners() {
        binding.iconStartNav.setOnClickListener {
            openNavigationView()
        }

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.categoriesDrawerItem -> {
                    showFragment(categoriesFragment)
                    closeNavigationView()
                    true

                }

                R.id.settingsDrawerItem -> {
                    showFragment(SettingsFragment())
                    closeNavigationView()
                    true
                }

                else -> false
            }
        }
        binding.iconSearch.setOnClickListener {
            val intent = Intent(this@MainActivity, SearchActivity::class.java)
            startActivity(intent)
        }
    }



    private fun initFragments() {
        categoriesFragment = CategoriesFragment {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, NewsFragment(it.id), "")
                .addToBackStack("")
                .commit()
        }

    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment, "")
            .addToBackStack("")
            .commit()
    }

    private fun openNavigationView(){
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun closeNavigationView(){
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun startCategoriesFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, categoriesFragment, "")
            .commit()

    }

}
