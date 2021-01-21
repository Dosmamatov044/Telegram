package com.example.telegram.ui.fragments.history

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.telegram.R
import com.example.telegram.database.*
import com.example.telegram.models.CommonModel
import com.example.telegram.ui.fragments.base.BaseFragment
import com.example.telegram.utilits.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_history_video.*


class History_videoFragment(private val videoModel: CommonModel): BaseFragment(R.layout.fragment_history_video) {
    var models :List<CommonModel>?= emptyList()
    var adapter: HistoryVideoAdapter? = null

   lateinit var mRefMessages: DatabaseReference
   private  lateinit var mMessagesListener:AppValueEventListener
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  history_videoRecyclerView!!.setHasFixedSize(true)
     //   history_videoRecyclerView!!.layoutManager = LinearLayoutManager(context)
    //    mFirebaseDatabase2 = FirebaseDatabase.getInstance()
    //    mref2 = mFirebaseDatabase2!!.getReference("video")
  initRecyclerView()

 videoGet.setOnClickListener { attachImage() } }

    private fun attachImage() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "*/*"
        photoPickerIntent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*", "video/*"))
        startActivityForResult(photoPickerIntent, PICK_VIDEO_PHOTO_REQUEST_CODE)

    }

    fun initRecyclerView(){

      //  models.add(model("Bekzat","https://i.imgur.com/7bMqysJ.mp4"))
  // models.add(CommonModel("","","","",
  //         "","","","","","","","","",false,
  //         "","","","","belzat",
  //         "https://i.imgur.com/7bMqysJ.mp4"))
 //       adapter?.notifyDataSetChanged()




       // val snapHelper: SnapHelper = PagerSnapHelper()
      //  snapHelper.attachToRecyclerView(history_videoRecyclerView)
        val layoutManager = LinearLayoutManager(context)
        history_videoRecyclerView.layoutManager = layoutManager
        adapter = models?.let { HistoryVideoAdapter(context!!, it) }

mRefMessages= REF_DATABASE_ROOT
        .child(NODE_HISTORY).child(videoModel.id).child(NODE_MESSAGES)

        history_videoRecyclerView.adapter = adapter
        mMessagesListener= AppValueEventListener { dataSnapShot->



            models=dataSnapShot.children.map {
                it.getCommonModel() }

            adapter?.setList(models!!)
            mRefMessages.addValueEventListener(mMessagesListener)
         //
        //   mRefMessages?.addValueEventListener(mMessagesListener)
        }
        mRefMessages.addValueEventListener(mMessagesListener)



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

    override fun onPause() {
        super.onPause()
        mRefMessages.removeEventListener(mMessagesListener)
    }
}