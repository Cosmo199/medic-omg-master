package com.example.medicomgmester.ui.chat.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.model.ChatList
import kotlinx.android.synthetic.main.item_theme_chat.view.*

class ChatHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(data_chat_list: ChatList) {
        itemView.apply {
            if (data_chat_list.chat_msg_type.toString() =="1"){
                image_view_admin.visibility = View.GONE
                owner_message.visibility = View.GONE
                message.visibility = View.GONE
                message_me.visibility = View.VISIBLE
                image_view_me.visibility = View.VISIBLE
                me_message.visibility = View.VISIBLE
                message_me.text = data_chat_list.chat_msg_message

            }else {
                image_view_me.visibility = View.GONE
                me_message.visibility = View.GONE
                message_me.visibility = View.GONE
                image_view_admin.visibility = View.VISIBLE
                owner_message.visibility = View.VISIBLE
                message.visibility = View.VISIBLE
                owner_message.text = data_chat_list.messmageBy
                message.text = data_chat_list.chat_msg_message
            }

          }
        }
    }

