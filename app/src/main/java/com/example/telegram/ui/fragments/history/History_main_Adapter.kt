package com.example.telegram.ui.fragments.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.R
import com.example.telegram.models.CommonModel
import com.example.telegram.utilits.downloadAndSetImage

class History_main_Adapter : RecyclerView.Adapter<History_main_Adapter.ViewHolder_history>() {

    val list= mutableListOf<CommonModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder_history {


    val view=LayoutInflater.from(parent.context).inflate(R.layout.history_main_item,parent,false)

       return ViewHolder_history(view)

    }

    override fun onBindViewHolder(holder: ViewHolder_history, position: Int) {

        holder.my_status.text="Мой статус"
        holder.my_photo.downloadAndSetImage(list[position].photoUrl)
        holder.my_prompt.text="Нажмите,чтобы добавить новый статус"

        holder.received_name.text=list[position].fullname
        holder.received_timeStamp.text=list[position].timeStamp.toString()
        holder.received_photo.downloadAndSetImage(list[position].photoUrl)

    }

    override fun getItemCount(): Int {
return list.size
    }




   inner class ViewHolder_history (view:View): RecyclerView.ViewHolder(view) {
    var my_status=view.findViewById<TextView>(R.id.history_main_my_status)
    var my_prompt=view.findViewById<TextView>(R.id.history_main_prompt)
    var my_photo=view.findViewById<ImageView>(R.id.history_main_photo_my)

    var received_name=view.findViewById<TextView>(R.id.history_main_user_name)
    var received_timeStamp=view.findViewById<TextView>(R.id.history_main_user_name)
    var received_photo=view.findViewById<ImageView>(R.id.history_main_user_name)


    }


}