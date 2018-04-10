package com.munna.firebasechat.model

import java.util.*

class ChatMessage {
    var chatMessage: String? = null
    var username: String? = null
    var messageTime: Long = 0
    constructor()
    constructor(chatMessage: String, username: String) {
        this.chatMessage = chatMessage
        this.username = username
        messageTime = Date().time
    }
}