package com.munna.firebasechat.utils

import android.content.Context
import android.text.format.DateFormat
import android.widget.Toast
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.FirebaseDatabase
import com.munna.firebasechat.R
import com.munna.firebasechat.holder.ChatMessageViewHolder
import com.munna.firebasechat.model.ChatMessage


fun getFormattedDate(chatMessage: ChatMessage): String {
    return DateFormat.format("dd-MM-yyyy hh:mm:ss a", chatMessage.messageTime) as String
}


fun showToast(context: Context, message: String) {
    Toast.makeText(context,
            message,
            Toast.LENGTH_LONG)
            .show()
}
