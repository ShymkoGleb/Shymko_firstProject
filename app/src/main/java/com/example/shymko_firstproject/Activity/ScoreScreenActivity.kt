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

        val TIME_CONST: Long = 10000
        var isGameStart = false
        var isGameCancel = false
        var isTimerRun = false
        var isScoreComparisonDone = false
        var scoreOfFirstTeam: Int = 0
        var scoreOfSecondTeam: Int = 0
        var timeForTimer = TIME_CONST
        lateinit var countDownTimer: CountDownTimer
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_screen)
        setupViews()
        setupButtomListener()
        startGame()
        Log.d("CustomLOG", "onCreate, isGameStart = $isGameStart, isGameCancel = $isGameCancel, isTimerRun = $isTimerRun")
    }

    fun setupViews() {
        var startNewGameFlag: Boolean = intent.getBooleanExtra("startNewGameFlag", false)
        Log.d(
            "CustomLOG",
            "setupViews  !startNewGameFlag = $startNewGameFlag  &&  isGameStart $isGameStart"
        )
        if (startNewGameFlag && !isGameStart && !isTimerRun) {
            Log.d(
                "CustomLOG",
                "setupViews  !startNewGameFlag = $startNewGameFlag  &&  isGameStart $isGameStart"
            )
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

        if (!startNewGameFlag && isGameStart) {
            Log.d(
                "CustomLOG",
                "setupViews  !startNewGameFlag = $startNewGameFlag  &&  isGameStart $isGameStart"
            )
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
            countDownTimer.cancel()
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
        /// start the game///
        if (isGameStart && !isGameCancel) {
            Log.d("CustomLOG", "1st if = isGameStart $isGameStart && isGameCancel $isGameCancel")
            btnAddPointToFirtsTeam.isClickable = true
            btnAddPointToSecondTeam.isClickable = true
            timeCalculation()
        }
        /// after counter stop
        if (!isGameStart && !isGameCancel && !isScoreComparisonDone) {
            Log.d("CustomLOG", "2nd if = isGameStart $isGameStart && isGameCancel $isGameCancel")
            btnAddPointToFirtsTeam.isClickable = false
            btnAddPointToSecondTeam.isClickable = false
            btnAddPointToFirtsTeam.isVisible = false
            btnAddPointToSecondTeam.isVisible = false
            btnCancelGame.isVisible = false
            scoreComparison()
            isScoreComparisonDone = true
        }
        /// if cancel is pressed
        if (isGameStart && isGameCancel) {
            Log.d("CustomLOG", "3rd if = isGameStart $isGameStart && isGameCancel $isGameCancel")
            isGameStart = false
            countDownTimer.cancel()
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
        countDownTimer = object : CountDownTimer(timeForTimer, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                isTimerRun=true
                timeForTimer = millisUntilFinished
                tvCountdownTimer.text = "Timer: " + millisUntilFinished / 1000
                tvScoreOfFirstTeam.text = scoreOfFirstTeam.toString()
                tvScoreOfSecondTeam.text = scoreOfSecondTeam.toString()
            }

            override fun onFinish() {
                isTimerRun=false
                isGameStart = false
                tvCountdownTimer.text = "Timer: " + "is UP"
                startGame()
            }
        }.start()
    }

    fun scoreComparison() {
        if (!isGameStart && !isGameCancel && !isTimerRun&& !isScoreComparisonDone) {
            if (scoreOfFirstTeam > scoreOfSecondTeam) {
                val firstTeam: String = intent.getStringExtra("firstTeam").toString()
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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("timeForTimer", timeForTimer)
        outState.putBoolean("isGameStart", isGameStart)
        outState.putBoolean("isGameCancel", isGameCancel)
        outState.putBoolean("isTimerRun", isTimerRun)
        outState.putInt("scoreOfFirstTeam", scoreOfFirstTeam)
        outState.putInt("scoreOfSecondTeam", scoreOfSecondTeam)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        timeForTimer = savedInstanceState.getLong("timeForTimer")
        isGameStart = savedInstanceState.getBoolean("isGameStart")
        isGameCancel = savedInstanceState.getBoolean("isGameCancel")
        isTimerRun = savedInstanceState.getBoolean("isTimerRun")
        scoreOfFirstTeam = savedInstanceState.getInt("scoreOfFirstTeam")
        scoreOfSecondTeam = savedInstanceState.getInt("scoreOfSecondTeam")
        if (isGameStart) {
            //tvScoreOfFirstTeam.text = scoreOfFirstTeam.toString()
            //tvScoreOfSecondTeam.text = scoreOfSecondTeam.toString()
            timeCalculation()
        }
    }
}