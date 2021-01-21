package com.example.shymko_firstproject.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shymko_firstproject.R

class WinnerScreenActivity : AppCompatActivity() {
    companion object{
        fun start(context: Context) {
            val intent = Intent(context, WinnerScreenActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner_screen)
    }
}