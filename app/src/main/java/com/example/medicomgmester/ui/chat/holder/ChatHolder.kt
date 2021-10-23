package com.example.medicomgmester.ui.chat.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.model.ChatList
import kotlinx.android.synthetic.main.item_theme_chat.view.*

class ChatHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(data_chat_list: ChatList) {
        itemView.apply {
            owner_message.text = data_chat_list.messmageBy
            message.text = data_chat_list.chat_msg_message

          }
        }
    }

