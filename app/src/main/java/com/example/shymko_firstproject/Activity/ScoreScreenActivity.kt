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
        var startGame = true
        var scoreOfFirstTeam:Int = 0
        var scoreOfSecondTeam:Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_screen)
        startGame = true
        scoreOfFirstTeam = 0
        scoreOfSecondTeam = 0
        tvScoreOfFirstTeam.text = scoreOfFirstTeam.toString()
        tvScoreOfSecondTeam.text = scoreOfSecondTeam.toString()
        Log.d("CustomDebug","OnCreate 1st, startGame= $startGame")
        val firstTeam: String? = intent.getStringExtra("firstTeam")
        val secondTeam: String? = intent.getStringExtra("secondTeam")
        onStart()
    }

    override fun onStart() {
        super.onStart()
        if(startGame==true){
            btnAddPointToFirtsTeam.isClickable = true
            btnAddPointToSecondTeam.isClickable = true
            timeCalculation()
            Log.d("CustomDebug","OnStart 1st, startGame= $startGame")
            btnAddPointToFirtsTeam.setOnClickListener{
                scoreOfFirstTeam ++
            }
            btnAddPointToSecondTeam.setOnClickListener{
                scoreOfSecondTeam ++
            }

        }else{
            btnAddPointToFirtsTeam.isClickable = false
            btnAddPointToSecondTeam.isClickable = false

            Toast.makeText(this,"Game is over",Toast.LENGTH_SHORT)
        }

    }

    fun timeCalculation() {

        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvCountdownTimer.text = "Timer: " + millisUntilFinished / 1000
                tvScoreOfFirstTeam.text = scoreOfFirstTeam.toString()
                tvScoreOfSecondTeam.text = scoreOfSecondTeam.toString()
            }

            override fun onFinish() {
                //Toast.makeText(, "TIME IS UP", Toast.LENGTH_SHORT).show()
                Log.d("CustomDebug", "onFinish, TIME IS UP")
                startGame = false
                Log.d("CustomDebug", "onFinish, startGame ==$startGame")
                tvCountdownTimer.text = "Timer: " + "is UP"
                onStart()
            }
        }.start()
    }
}