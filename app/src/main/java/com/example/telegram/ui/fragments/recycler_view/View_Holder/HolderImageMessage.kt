package com.example.telegram.ui.fragments.recycler_view.View_Holder


import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.database.CURRENT_UID
import com.example.telegram.ui.fragments.recycler_view_views.MessageView
import com.example.telegram.utilits.asTime
import com.example.telegram.utilits.downloadAndSetImage
import kotlinx.android.synthetic.main.message_item_image.view.*


class HolderImageMessage(view: View) : RecyclerView.ViewHolder(view), MessageHolder {
     var context: Context=itemView.context
    var isImageFitToScreen = false



    private var isImageScaled = false
    private  val blocReceivedImageMessage: ConstraintLayout = view.bloc_received_image_message
    private  val blocUserImageMessage: ConstraintLayout = view.bloc_user_image_message
    private  var chatUserImage: ImageView = view.chat_user_image
    private  var chatUserImage_full: ImageView = view.chat_user_image_full
    private  val chatReceivedImage: ImageView = view.chat_received_image
    private  val chatUserImageMessageTime: TextView = view.chat_user_image_message_time
    private  val chatReceivedImageMessageTime: TextView = view.chat_received_image_message_time



    @RequiresApi(Build.VERSION_CODES.M)
    override fun drawMessage(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blocReceivedImageMessage.visibility = View.GONE
            blocUserImageMessage.visibility = View.VISIBLE
            chatUserImage.downloadAndSetImage(view.fileUrl)
        chatUserImage_full.downloadAndSetImage(view.fileUrl)


        

          chatUserImage.setOnClickListener {

              if (!isImageScaled) chatUserImage_full.visibility=View.GONE


              //it.animate().scaleX(1.4f).scaleY(1.4f).duration = 500;
             if (isImageScaled) chatUserImage_full.visibility=View.VISIBLE

                 // it.animate().scaleX(1f).scaleY(1f).duration = 500;
                 isImageScaled = !isImageScaled;

          }

            chatUserImageMessageTime.text =
                    view.timeStamp.asTime()
        } else {
            blocReceivedImageMessage.visibility = View.VISIBLE
            blocUserImageMessage.visibility = View.GONE
            chatReceivedImage.downloadAndSetImage(view.fileUrl)
            chatReceivedImageMessageTime.text =
                    view.timeStamp.asTime()
        }
    }

    override fun onAttach(view: MessageView) {
    }

    override fun onDetach() {
    }




}