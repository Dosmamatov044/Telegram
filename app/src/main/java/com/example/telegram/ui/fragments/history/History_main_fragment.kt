package com.example.telegram.ui.fragments.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.R
import com.example.telegram.models.CommonModel
import kotlinx.android.synthetic.main.history_main_fragment.*


class History_main_fragment : Fragment() {
   lateinit var  mAdapter:History_main_Adapter
   var mRecyclerView=history_recycler_view
    var mList= mutableListOf<CommonModel>()




   private fun initRecyclerView(){

        mAdapter= History_main_Adapter()

     mRecyclerView.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

      mRecyclerView.adapter=mAdapter


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

    }
}


