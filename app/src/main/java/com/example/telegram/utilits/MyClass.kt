package com.example.telegram.utilits

import android.content.Context

abstract class MyClass {

    companion object {

        private lateinit var contex: Context

        fun setContext(con: Context) {
            contex=con
        }
    }
}