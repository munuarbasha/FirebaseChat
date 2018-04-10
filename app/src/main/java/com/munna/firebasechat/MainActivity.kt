package com.munna.firebasechat

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.database.FirebaseDatabase
import com.munna.firebasechat.utils.getFormattedDate
import com.munna.firebasechat.utils.showToast
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import com.munna.firebasechat.model.ChatMessage
import com.firebase.ui.database.FirebaseListAdapter


class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeFireBaseLogin()

        sendMessage.setOnClickListener({
            var userName = FirebaseAuth.getInstance().currentUser!!.displayName!!
            FirebaseDatabase.getInstance()
                    .reference
                    .push()
                    .setValue(ChatMessage(chatInput.text.toString(), userName)
                    )

            chatInput.setText("")
        })

        var adapter = object : FirebaseListAdapter<ChatMessage>(this, ChatMessage::class.java,
                R.layout.item_chats, FirebaseDatabase.getInstance().reference) {
            override fun populateView(v: View, model: ChatMessage, position: Int) {
                val messageText = v.findViewById(R.id.chatMessage) as TextView
                val messageUser = v.findViewById(R.id.username) as TextView
                val messageTime = v.findViewById(R.id.messageTime) as TextView
                messageText.text = model.chatMessage
                messageUser.text = model.username
                messageTime.text = getFormattedDate(model)
            }
        }

        listChatMessage.adapter = adapter

    }

    private fun initializeFireBaseLogin() {
        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    REQUEST_CODE
            )
        } else {
            showToast(this, getString(R.string.welcome) + " " + FirebaseAuth.getInstance().currentUser?.displayName)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener {
                        showToast(this@MainActivity, getString(R.string.signed_out))
                        finish()
                    }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                showToast(this, getString(R.string.login_success))
            } else {
                showToast(this, getString(R.string.login_failure))
                finish()
            }
        }
    }

}
