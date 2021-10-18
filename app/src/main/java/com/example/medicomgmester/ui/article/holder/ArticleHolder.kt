package com.example.medicomgmester.ui.article.holder

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.extension.load
import com.example.medicomgmester.model.Article
import com.example.medicomgmester.ui.article.DetailArticleActivity
import kotlinx.android.synthetic.main.item_theme_article.view.*

class ArticleHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(data_article_list: Article) {
        itemView.apply {
            name_article.text = data_article_list.article_name
            detail_article.text = data_article_list.detail
            image_view_article.load(data_article_list.image_01)
            layout_article_bg.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, DetailArticleActivity::class.java)
                        .putExtra(DetailArticleActivity.Key, data_article_list)
                )
            }
        }
    }
}

