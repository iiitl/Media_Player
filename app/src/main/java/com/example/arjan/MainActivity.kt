package com.example.arjan

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import com.google.android.material.slider.Slider
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {

    private var mp : MediaPlayer? = null
    private lateinit var play : ImageView
    private lateinit var pause : ImageView
    private lateinit var seekbar: SeekBar
    private var currentSong = mutableListOf(R.raw.arjan)
    private var noc=0;
    private lateinit var btn_like:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play= findViewById(R.id.imageView5)
        pause= findViewById(R.id.imageView4)
        seekbar = findViewById(R.id.progress)
        btn_like = findViewById(R.id.btn_like)
        btn_like.setOnClickListener(View.OnClickListener {
            change()
        })

        controlSound(currentSong[0])
    }

    private fun controlSound(id: Int){
        play.setOnClickListener {
            if(mp==null){
                mp=MediaPlayer.create(this,id)
                Log.d("MainActivity","ID: ${mp!!.audioSessionId}")
                initialiseSeekbar()
            }
            mp?.start()
            Log.d("MainActivity","ID: ${mp!!.duration/1000} seconds")
        }
        pause.setOnClickListener {
            if(mp!=null) mp?.pause()
            Log.d("MainActivity","ID: ${mp!!.currentPosition/1000} seconds")

        }

    }

    private fun initialiseSeekbar(){
        seekbar.max = mp!!.duration

        val handler = android.os.Handler()
        handler.postDelayed(object :Runnable{
            override fun run(){
                try {
                    seekbar.progress = mp!!.currentPosition
                    handler.postDelayed(this,1000)
                }
                catch (e:Exception){
                    seekbar.progress = 0
                }
            }
        },1000)
    }
    fun change()
    {
        if(noc%2==0)
        {
            btn_like.setImageResource(R.drawable.heart)
        }
        else
            btn_like.setImageResource(R.drawable.heartwhitecom)
        noc++;
    }
}