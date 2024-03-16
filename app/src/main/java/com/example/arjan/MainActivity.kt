package com.example.arjan

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var mp: MediaPlayer? = null
    private lateinit var play: ImageView
    private lateinit var prev: ImageView
    private lateinit var card: ImageView
    private lateinit var songTitle: TextView
    private lateinit var next: ImageView
    private lateinit var seekbar: SeekBar
    private var currentSong = mutableListOf(R.raw.arjan, R.raw.bekhayalii,R.raw.sajde,R.raw.ehsan)
    private var currentSongName =
        mutableListOf("Arjan Vailly(From Animal)", "Bekhayali(From Kabir Singh)","Sajde(From Kill-Dil)","Ehsan tera hoga (From Sanam)")
    private var songCard = mutableListOf(R.drawable.arjan, R.drawable.bekhali,R.drawable.sajdecard,R.drawable.ehsancard)
    private var noc = 0
    private var ps = 0;
    private var currentSongindex = 0
    private lateinit var btn_like: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play = findViewById(R.id.playpause)
        card = findViewById(R.id.card)
        prev = findViewById(R.id.pre)
        next = findViewById(R.id.imageView4)
        seekbar = findViewById(R.id.progress)
        songTitle = findViewById(R.id.songTitle)
        btn_like = findViewById(R.id.btn_like)

        btn_like.setOnClickListener(View.OnClickListener {
            change()
        })

        controlSound(currentSong[0])
        next.setOnClickListener(View.OnClickListener { next() })
        prev.setOnClickListener(View.OnClickListener { prev() })
    }


    private fun controlSound(id: Int) {
        play.setOnClickListener {
            if (ps++ % 2 == 0) {
                play.setImageResource(R.drawable.finalp)
                if (mp == null) {
                    mp = MediaPlayer.create(this, id)
                    Log.d("MainActivity", "ID: ${mp!!.audioSessionId}")
                    initialiseSeekbar()
                }
                mp?.start()
                Log.d("MainActivity", "ID: ${mp!!.duration / 1000} seconds")
            } else {
                play.setImageResource(R.drawable.pause)
                if (mp != null) mp?.pause()
                Log.d("MainActivity", "ID: ${mp!!.currentPosition / 1000} seconds")
            }
        }


    }

    private fun initialiseSeekbar() {
        seekbar.max = mp!!.duration

        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekbar.progress = mp!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    seekbar.progress = 0
                }
            }
        }, 1000)
    }

    fun change() {
        if (noc % 2 == 0) {
            btn_like.setImageResource(R.drawable.heart)
        } else
            btn_like.setImageResource(R.drawable.heartwhitecom)
        noc++;
    }

    fun next() {
        if (currentSongindex < currentSong.size - 1) {
            ps=0
            play.setImageResource(R.drawable.pause)
            currentSongindex++
            songTitle.setText(currentSongName[currentSongindex])
            mp?.reset()
            mp=null

            controlSound(currentSong[currentSongindex])

            card.setImageResource(songCard[currentSongindex])




        }
        else
        {
            Toast.makeText(this, "No more songs", Toast.LENGTH_SHORT).show()
        }

    }

    fun prev() {
        if (currentSongindex > 0) {
            ps = 0
            currentSongindex--
            play.setImageResource(R.drawable.pause)
            songTitle.setText(currentSongName[currentSongindex])
            mp?.reset()
            mp = null
            controlSound(currentSong[currentSongindex])
            card.setImageResource(songCard[currentSongindex])

        }
        else
        {
            Toast.makeText(this, "No more songs", Toast.LENGTH_SHORT).show()
        }
        }
    }
