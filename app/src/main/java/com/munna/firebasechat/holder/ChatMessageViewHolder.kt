package com.munna.firebasechat.holder

import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.munna.firebasechat.R


class ChatMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val chatMessage = itemView.findViewById(R.id.chatMessage) as TextView
    val messageUser = itemView.findViewById(R.id.username) as TextView
    val messageTime = itemView.findViewById(R.id.messageTime) as TextView
}