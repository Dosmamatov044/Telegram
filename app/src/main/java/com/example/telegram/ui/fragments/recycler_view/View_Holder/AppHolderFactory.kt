package com.example.telegram.ui.fragments.recycler_view.View_Holder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.R
import com.example.telegram.ui.fragments.recycler_view_views.MessageView

class AppHolderFactory {



    companion object {

        fun getHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                MessageView.MESSAGE_IMAGE -> {
                    val view = LayoutInflater.from(parent.context)
                            .inflate(R.layout.message_item_image, parent, false)
                    HolderImageMessage(view)
                }

                MessageView.MESSAGE_VOICE -> {
                    val view = LayoutInflater.from(parent.context)
                            .inflate(R.layout.message_item_voice, parent, false)
                    HolderVoiceMessage(view)
                }

                MessageView.MESSAGE_FILE -> {
                    val view = LayoutInflater.from(parent.context)
                            .inflate(R.layout.message_item_file, parent, false)
                    HolderFileMessage(view)
                }

                else ->{
                    val view = LayoutInflater.from(parent.context)
                            .inflate(R.layout.message_item_text, parent, false)
                    HolderTextMessage(view)
                }
            }
        }
    }
}