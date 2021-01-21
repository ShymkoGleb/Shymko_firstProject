package com.example.shymko_firstproject.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shymko_firstproject.R

class ScoreScreenActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
              val intent = Intent(context, ScoreScreenActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_screen)
       // timeCalculation()


    }


    /*fun timeCalculation() {

        var timeOfHid: Long = ((tvCounter + 1) / 10) * 1000
        object : CountDownTimer(timeOfHid, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                startTriger = false
                Log.d("onFinish", "startTriger $startTriger")
                onStart()
            }
        }.start()

    }*/
}