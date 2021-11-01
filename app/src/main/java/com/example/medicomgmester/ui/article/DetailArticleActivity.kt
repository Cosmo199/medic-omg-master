package com.example.medicomgmester.ui.article

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.medicomgmester.MenuActivity
import com.example.medicomgmester.R
import com.example.medicomgmester.extension.load
import com.example.medicomgmester.model.Article
import com.example.medicomgmester.ui.chat.ChatActivity
import kotlinx.android.synthetic.main.activity_detail_article.*
import kotlinx.android.synthetic.main.activity_detail_article_sub.*
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_gallery.fab
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
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        fab_article.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            this.startActivity(intent)
        }

    }


}