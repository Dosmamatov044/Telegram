package com.example.telegram.ui.fragments.history

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.R
import com.example.telegram.database.*

import com.example.telegram.models.CommonModel
import com.example.telegram.ui.fragments.base.BaseFragment
import com.example.telegram.utilits.AppValueEventListener
import com.example.telegram.utilits.PICK_VIDEO_PHOTO_REQUEST_CODE
import com.example.telegram.utilits.TYPE_MESSAGE_VIDEO
import com.example.telegram.utilits.getFilenameFromUri
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_history_video.*


class History_videoFragment(private var videoModel: CommonModel): BaseFragment(R.layout.fragment_history_video) {

   var list= mutableListOf<CommonModel>()
    var mRecyclerView2: RecyclerView? = null
    var mFirebaseDatabase2: FirebaseDatabase? = null
    var mref2: DatabaseReference? = null
    private  lateinit var mMessagesListener: AppValueEventListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView2 = view.findViewById(R.id.history_videoRecyclerView)
        mRecyclerView2!!.setHasFixedSize(true)
        mRecyclerView2!!.layoutManager = LinearLayoutManager(context)
        mFirebaseDatabase2 = FirebaseDatabase.getInstance()


        mref2= REF_DATABASE_ROOT
                .child("history").child(videoModel.id).child("messages").child(videoModel.videoUrl)
        videoGet.setOnClickListener { attachImage() } }

private fun attachImage() {
    val photoPickerIntent = Intent(Intent.ACTION_PICK)
    photoPickerIntent.type = "*/*"
    photoPickerIntent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*", "video/*"))
    startActivityForResult(photoPickerIntent, PICK_VIDEO_PHOTO_REQUEST_CODE)

}



     override fun onStart() {
        super.onStart()
        val options: FirebaseRecyclerOptions<CommonModel> = mref2?.let {
            FirebaseRecyclerOptions.Builder<CommonModel>()
                    .setQuery(it, CommonModel::class.java)
                    .build()
        } as FirebaseRecyclerOptions<CommonModel>
        val firebaseRecyclerAdapter: FirebaseRecyclerAdapter<CommonModel, ViewHolder> = object : FirebaseRecyclerAdapter<CommonModel, ViewHolder>(options) {
             override fun onBindViewHolder(holder: ViewHolder, position: Int, model: CommonModel) {


                if (holder.isPlaying()) {
                    Log.e("TAG1", "play");
                    holder.releasePlayer();
                    holder.intiPlayer(model.videoUrl);
                } else {
                    Log.e("TAG1", "empty");
                    holder.intiPlayer(model.videoUrl);
                }
             holder.textView?.text=model.timeStamp.toString()


            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.history_video_item, parent, false)
                return ViewHolder(view,context!!)
            }
        }
        firebaseRecyclerAdapter.startListening()
        mRecyclerView2!!.adapter = firebaseRecyclerAdapter
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        /* Активность которая запускается для получения картинки для фото пользователя */
        super.onActivityResult(requestCode, resultCode, data)
        if (data!=null){
            when(requestCode) {
                PICK_VIDEO_PHOTO_REQUEST_CODE -> {
                    val uri = data.data
                    val messageKey = getMessageKey(videoModel.id)
                    val videoName = getFilenameFromUri(uri!!)
                    uploadVideoToStorage(uri, messageKey, videoModel.id, TYPE_MESSAGE_VIDEO, videoName)

                }
            }}}

}