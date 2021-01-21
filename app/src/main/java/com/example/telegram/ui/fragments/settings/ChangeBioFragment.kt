package com.example.telegram.ui.fragments.settings

import com.example.telegram.R
import com.example.telegram.database.USER
import com.example.telegram.database.setBioToDatabase
import com.example.telegram.ui.fragments.base.BaseChangeFragment
import com.example.telegram.utilits.*
import kotlinx.android.synthetic.main.fragment_change_bio.*

/* Фрагмент для изменения информации о пользователе */

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio = settings_input_bio.text.toString()
        setBioToDatabase(newBio)
    }
}