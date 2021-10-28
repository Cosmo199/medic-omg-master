package com.example.medicomgmester.ui.article

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.medicomgmester.R
import com.example.medicomgmester.extension.load
import com.example.medicomgmester.model.Article
import kotlinx.android.synthetic.main.activity_detail_article_sub.*
import kotlinx.android.synthetic.main.view_action_bar.*


class DetailArticleActivity : AppCompatActivity() {

    companion object {
        const val Key = "KEY_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_article)
        serEvent()
        setUI()
    }

    private fun setUI() {
        val data: Article? = intent.getParcelableExtra(Key)
        text_bar.text = "บทความ"
        image_article_activity.load(data?.image_01)
        detail_article_activity.text = data?.detail
        name_author.text = "เขียนโดย: "+data?.author
    }

    private fun serEvent() {
        arrow_back.setOnClickListener {
            val fragment: Fragment = ArticleFragment()
            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.nav_article, fragment).commit()
        }
    }


}