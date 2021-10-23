package com.example.medicomgmester.ui.article.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.R
import com.example.medicomgmester.model.Article
import com.example.medicomgmester.ui.article.holder.ArticleHolder

class AdapterArticle(private var coWork: List<Article>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(items: List<Article>) {
        coWork = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ArticleHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = coWork.size

    override fun getItemViewType(position: Int): Int = R.layout.item_theme_article

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ArticleHolder).onBind(coWork[position])


}