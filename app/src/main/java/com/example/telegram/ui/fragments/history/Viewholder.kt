package com.example.telegram.ui.fragments.history


import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.R
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


class ViewHolder(itemView: View,val context: Context) : RecyclerView.ViewHolder(itemView) {
    private var exoPlayerView: SimpleExoPlayerView? = null
    private var exoPlayer: SimpleExoPlayer? = null
    private var playbackPosition: Long = 0
    private var currentWindow = 0
    private var playWhenReady = false
      var textView:TextView?=null

    //    var title :TextView

    init {
        exoPlayerView =itemView.findViewById(R.id.exoplayer_view)
          textView=itemView.findViewById(R.id.textViewTest)
    // title=view.findViewById(R.id.titleTv)
    }



    fun intiPlayer(url: String) {
        try {

            val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
            val trackSelector: TrackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
            exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
            val videoURI = Uri.parse(url)
            val dataSourceFactory = DefaultHttpDataSourceFactory("video")
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
}}