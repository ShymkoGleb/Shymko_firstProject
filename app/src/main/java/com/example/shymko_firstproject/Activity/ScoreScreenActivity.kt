package com.example.shymko_firstproject.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.shymko_firstproject.R
import com.example.shymko_firstproject.Winner
import kotlinx.android.synthetic.main.activity_score_screen.*

class ScoreScreenActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ScoreScreenActivity::class.java)
            context.startActivity(intent)
        }

        val TIME_CONST: Long = 5000
        var isGameStart = true
        var isGameCancel = false
        var scoreOfFirstTeam: Int = 0
        var scoreOfSecondTeam: Int = 0
        var timeForTimer = TIME_CONST
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_screen)
        setupViews()
        setupButtomListener()
        startGame()
    }

    fun setupViews() {
        var startNewGameFlag: Boolean = intent.getBooleanExtra("startNewGameFlag",false)
        if (startNewGameFlag){
            isGameStart = true
            isGameCancel = false
            scoreOfFirstTeam = 0
            scoreOfSecondTeam = 0
            timeForTimer = TIME_CONST
            tvScoreOfFirstTeam.text = scoreOfFirstTeam.toString()
            tvScoreOfSecondTeam.text = scoreOfSecondTeam.toString()
            val firstTeam: String = intent.getStringExtra("firstTeam").toString()
            val secondTeam: String = intent.getStringExtra("secondTeam").toString()
            tvNameOfFirstTeam.text = firstTeam
            tvNameOfSecondTeam.text = secondTeam
            startNewGameFlag = false
        }
    }

    fun setupButtomListener() {
        btnAddPointToFirtsTeam.setOnClickListener {
            scoreOfFirstTeam++
        }
        btnAddPointToSecondTeam.setOnClickListener {
            scoreOfSecondTeam++
        }
        btnCancelGame.setOnClickListener {
            Log.d("CustomLOG", "btnCancelGame.setOnClickListener = isGameCancel $isGameCancel")
            // isGameStart = false
            isGameCancel = true
            startGame()
            Log.d("CustomLOG", "btnCancelGame.setOnClickListener = isGameCancel $isGameCancel")
        }
        btnGotoMainFromSS.setOnClickListener {
            MainActivity.start(this)
        }
        btnGotoLOWFromSS.setOnClickListener {
            ListOfWinnersActivity.start(this)
        }
    }

    fun startGame() {
        if (isGameStart && !isGameCancel) {
            Log.d("CustomLOG", "1st if = isGameStart $isGameStart && isGameCancel $isGameCancel")
            btnAddPointToFirtsTeam.isClickable = true
            btnAddPointToSecondTeam.isClickable = true
            timeCalculation()
        }
        if (!isGameStart && !isGameCancel) {
            Log.d("CustomLOG", "2nd if = isGameStart $isGameStart && isGameCancel $isGameCancel")
            btnAddPointToFirtsTeam.isClickable = false
            btnAddPointToSecondTeam.isClickable = false
            btnAddPointToFirtsTeam.isVisible = false
            btnAddPointToSecondTeam.isVisible = false
            btnCancelGame.isVisible = false
            scoreComparison()
        }
        if (isGameStart && isGameCancel) {
            Log.d("CustomLOG", "3rd if = isGameStart $isGameStart && isGameCancel $isGameCancel")
            //isGameStart = false
            tvCountdownTimer.isVisible = false
            tvGameIsCanceled.isVisible = true
            btnAddPointToFirtsTeam.isClickable = false
            btnAddPointToSecondTeam.isClickable = false
            btnAddPointToFirtsTeam.isVisible = false
            btnAddPointToSecondTeam.isVisible = false
            btnCancelGame.isVisible = false
            btnGotoLOWFromSS.isVisible = true
            btnGotoMainFromSS.isVisible = true
            btnGotoLOWFromSS.isClickable = true
            btnGotoMainFromSS.isClickable = true
        }
    }

    fun timeCalculation() {
        object : CountDownTimer(timeForTimer, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeForTimer = millisUntilFinished
                tvCountdownTimer.text = "Timer: " + millisUntilFinished / 1000
                tvScoreOfFirstTeam.text = scoreOfFirstTeam.toString()
                tvScoreOfSecondTeam.text = scoreOfSecondTeam.toString()
            }

            override fun onFinish() {
                isGameStart = false
                tvCountdownTimer.text = "Timer: " + "is UP"
                //onStart()
                startGame()
            }
        }.start()
    }

    fun scoreComparison() {
        val firstTeam: String = intent.getStringExtra("firstTeam").toString()
        if (scoreOfFirstTeam > scoreOfSecondTeam) {
            Winner.listOfWinner.add(Winner(firstTeam, scoreOfFirstTeam))
            val intent = Intent(this, WinnerScreenActivity::class.java)
            intent.putExtra("winner", firstTeam)
            intent.putExtra("score", scoreOfFirstTeam.toString())
            startActivity(intent)
        }
        if (scoreOfFirstTeam < scoreOfSecondTeam) {
            val secondTeam: String = intent.getStringExtra("secondTeam").toString()
            Winner.listOfWinner.add(Winner(secondTeam, scoreOfSecondTeam))
            val intent = Intent(this, WinnerScreenActivity::class.java)
            intent.putExtra("winner", secondTeam)
            intent.putExtra("score", scoreOfSecondTeam.toString())
            startActivity(intent)
        }
        if (scoreOfFirstTeam == scoreOfSecondTeam) {
            val intent = Intent(this, WinnerScreenActivity::class.java)
            intent.putExtra("winner", "No winner in this game")
            intent.putExtra("score", "The score is tie")
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("timeForTimer", timeForTimer)
        outState.putBoolean("isGameStart", isGameStart)
        outState.putBoolean("isGameCancel", isGameCancel)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        timeForTimer = savedInstanceState.getLong("timeForTimer")
        isGameStart = savedInstanceState.getBoolean("isGameStart")
        isGameCancel = savedInstanceState.getBoolean("isGameCancel")
        if(isGameStart){
            startGame()
        }
    }
}