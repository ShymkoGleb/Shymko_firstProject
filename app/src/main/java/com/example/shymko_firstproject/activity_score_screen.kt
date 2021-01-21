package com.example.shymko_firstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log

class activity_score_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_screen)
        timeCalculation()
    }


    fun timeCalculation() {
        if (triger) {
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
        }
    }
}