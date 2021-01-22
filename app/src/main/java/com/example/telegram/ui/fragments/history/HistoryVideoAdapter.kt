 package com.example.telegram.ui.fragments.history



import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.R
import com.example.telegram.models.CommonModel
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory


class HistoryVideoAdapter(val context: Context, private var list1: List<CommonModel> = emptyList()): RecyclerView.Adapter<HistoryVideoAdapter.ViewHolderVideo>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVideo {
    val  view=LayoutInflater.from(parent.context).inflate(R.layout.history_video_item, parent, false)
        return ViewHolderVideo(view, context)
    }

    override fun onBindViewHolder(holder: ViewHolderVideo, position: Int) {

        if (holder.isPlaying()) {
            Log.e("TAG1", "play");
            holder.releasePlayer();
            holder.intiPlayer(list1[position].videoUrl);
        } else {
            Log.e("TAG1", "empty");
            holder.intiPlayer(list1[position].videoUrl);
        }


       // holder.title.text=list1[position].title

    }

    override fun getItemCount(): Int {



        return  list1.size
    }



    class ViewHolderVideo(view: View, var context: Context):RecyclerView.ViewHolder(view){

        private var exoPlayerView: SimpleExoPlayerView? = null
        private var exoPlayer: SimpleExoPlayer? = null
        private var playbackPosition: Long = 0
        private var currentWindow = 0
        private var playWhenReady = false


    //    var title :TextView

        init {
            exoPlayerView =view.findViewById(R.id.exoplayer_view)
           // title=view.findViewById(R.id.titleTv)
        }



        fun intiPlayer(url: String) {
            try {

                val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
                val trackSelector: TrackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
                exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
                val videoURI = Uri.parse(url)
                val dataSourceFactory = DefaultHttpDataSourceFactory("exoplayer_video")
                val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()
                val mediaSource: MediaSource = ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null)
                exoPlayerView!!.player = exoPlayer
              val ex=exoPlayer
               ex?.prepare(mediaSource)
                ex?.playWhenReady = true
            } catch (e: Exception) {
                Log.e("MainAcvtivity", " exoplayer error $e")
            }
        }

        fun isPlaying(): Boolean {
            return exoPlayer != null && exoPlayer!!.playbackState != Player.STATE_ENDED && exoPlayer!!.playbackState != Player.STATE_IDLE && exoPlayer!!.playWhenReady
        }

        fun releasePlayer() {
            if (exoPlayer != null) {
                playbackPosition = exoPlayer!!.currentPosition
                currentWindow = exoPlayer!!.currentWindowIndex
                playWhenReady = exoPlayer!!.playWhenReady
                exoPlayer!!.release()
                exoPlayer = null
            }
        }









}
fun setList(list:List<CommonModel>){
    list1=list
    notifyDataSetChanged()
}

}