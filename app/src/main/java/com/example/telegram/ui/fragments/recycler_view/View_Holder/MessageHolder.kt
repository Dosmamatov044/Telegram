package com.example.telegram.ui.fragments.recycler_view.View_Holder

import com.example.telegram.ui.fragments.recycler_view_views.MessageView

interface MessageHolder {
    fun drawMessage(view: MessageView)
    fun onAttach(view: MessageView)
    fun onDetach()
}