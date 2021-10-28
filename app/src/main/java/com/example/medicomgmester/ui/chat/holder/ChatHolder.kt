package com.example.medicomgmester.ui.chat.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.model.ChatList
import kotlinx.android.synthetic.main.item_theme_chat.view.*

class ChatHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(data_chat_list: ChatList) {
        itemView.apply {
            if (data_chat_list.chat_msg_type.toString() =="1"){
                admin_layout.visibility = View.GONE
                admin_name.visibility = View.GONE
                admin_message.visibility = View.GONE
                owner_layout.visibility = View.VISIBLE
                owner_name.visibility = View.VISIBLE
                owner_message.visibility = View.VISIBLE
                owner_message.text = data_chat_list.chat_msg_message
            }else {
                admin_layout.visibility = View.VISIBLE
                admin_name.visibility = View.VISIBLE
                admin_message.visibility = View.VISIBLE
                owner_layout.visibility = View.GONE
                owner_name.visibility = View.GONE
                owner_message.visibility = View.GONE
                admin_name.text = data_chat_list.messmageBy
                admin_message.text = data_chat_list.chat_msg_message
            }

        }
    }
    }

