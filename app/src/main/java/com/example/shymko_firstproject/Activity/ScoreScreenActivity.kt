package com.example.shymko_firstproject.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.example.shymko_firstproject.R
import kotlinx.android.synthetic.main.activity_score_screen.*

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

        val firstTeam: String? = intent.getStringExtra("firstTeam")
        val secondTeam: String? = intent.getStringExtra("secondTeam")
        timeCalculation()
    }

    override fun onStart() {
        super.onStart()
        fun timeCalculation() {

        }
    }


    fun timeCalculation() {

        var timeOfHid: Long = ((100 + 1) / 10) * 1000
        object : CountDownTimer(timeOfHid, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("onTick", "hello on tick")
                tvCountdownTimer.text = "Timer: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                //Toast.makeText(, "TIME IS UP", Toast.LENGTH_SHORT).show()
                Log.d("onFinish", "TIME IS UP")
                onStart()
            }
        }.start()

    }
}