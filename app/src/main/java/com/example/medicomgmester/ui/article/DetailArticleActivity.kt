package com.example.medicomgmester.ui.article

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medicomgmester.MenuActivity
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
    }

    private fun serEvent() {
        arrow_back.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

}