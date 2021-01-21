package com.example.shymko_firstproject.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shymko_firstproject.R
import kotlinx.android.synthetic.main.activity_list_of_winners.*

class ListOfWinnersActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ListOfWinnersActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_winners)

        btnGoMainFromLOW.setOnClickListener{
            MainActivity.start(this)
        }
    }
}