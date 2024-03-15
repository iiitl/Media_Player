package com.example.arjan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        val imageView = findViewById<ImageView>(R.id.logo)

        // Load the fade animation
        val fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade)

        // Apply the animation to the image view
        imageView.startAnimation(fadeAnimation)

        Handler().postDelayed(Runnable {

            val i = Intent(this@splash, MainActivity::class.java)
            startActivity(i)
        }, 2000)
    }
}