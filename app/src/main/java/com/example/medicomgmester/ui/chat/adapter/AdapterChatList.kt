package com.example.medicomgmester.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.R
import com.example.medicomgmester.model.ChatList
import com.example.medicomgmester.ui.chat.holder.ChatHolder

class AdapterChatList(private var coWork: List<ChatList>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(items: List<ChatList>) {
        coWork = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ChatHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = coWork.size

    override fun getItemViewType(position: Int): Int = R.layout.item_theme_chat

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ChatHolder).onBind(coWork[position])


}