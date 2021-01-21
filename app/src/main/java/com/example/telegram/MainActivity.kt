package com.example.telegram
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.telegram.`object`.AppDrawer
import com.example.telegram.database.AUTH
import com.example.telegram.database.initFirebase
import com.example.telegram.database.initUser
import com.example.telegram.databinding.ActivityMainBinding
import com.example.telegram.models.CommonModel
import com.example.telegram.ui.fragments.history.History_main_fragment
import com.example.telegram.ui.fragments.history.History_videoFragment

import com.example.telegram.ui.fragments.main_list.MainListFragment

import com.example.telegram.ui.fragments.register.EnterPhoneNumberFragment
import com.example.telegram.utilits.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    val videoModel:CommonModel=CommonModel()

    private lateinit var mBinding: ActivityMainBinding
    var mAppDrawer: AppDrawer=AppDrawer()
   var mToolbar: Toolbar?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        /* Функция запускается один раз, при создании активити */
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        bottomNavigationView()


        APP_ACTIVITY = this
        initFirebase()
        initUser {
            CoroutineScope(Dispatchers.IO).launch {
                initContacts()
            }
            initFields()
            initFunc()
        }

    }



    private fun initFunc() {
        /* Функция инициализирует функциональность приложения */

        setSupportActionBar(mToolbar)
        if (AUTH.currentUser != null) {
            mAppDrawer.create()
            replaceFragment(MainListFragment(), false)
        } else {
            replaceFragment(EnterPhoneNumberFragment(),false)
        }
    }

    private fun initFields() {
        /* Функция инициализирует переменные */
        mToolbar = mBinding.mainToolbar

    }

    override fun onStart() {
        super.onStart()
        AppStates.updateState(AppStates.ONLINE)
    }

    override fun onStop() {
        super.onStop()
        AppStates.updateState(AppStates.OFFLINE)
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(APP_ACTIVITY, READ_CONTACTS)==PackageManager.PERMISSION_GRANTED){
            initContacts()
        }
    }

fun bottomNavigationView(){

    bottom_navigation.setOnNavigationItemSelectedListener { item->

        when (item.itemId) {
            R.id. mainFragment_list-> supportFragmentManager.beginTransaction()
                    .replace(R.id.data_container, MainListFragment()).commit()
            R.id. history_fragment-> supportFragmentManager.beginTransaction()
                    .replace(R.id.data_container, History_videoFragment(videoModel)).commitAllowingStateLoss()

        }
        true

    }

}


}