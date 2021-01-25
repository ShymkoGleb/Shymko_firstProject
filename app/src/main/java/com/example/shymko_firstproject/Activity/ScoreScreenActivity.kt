package com.example.shymko_firstproject.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
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

        val TIME_CONST: Long = 11000
        var isGameStart = false
        var isGameCancel = false
        var isTimerRun = false
        var isScoreComparisonDone = false
        var scoreOfFirstTeam: Int = 0
        var scoreOfSecondTeam: Int = 0
        var timeForTimer = TIME_CONST
        lateinit var countDownTimer: CountDownTimer
        var firstTeam: String? = null
        var secondTeam: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_screen)
        setupViews()
        setupButtomListener()
        startGame()
    }

    fun setupViews() {
        // first run //
        if (!isGameStart && !isTimerRun && !isGameCancel) {
            isGameStart = true
            isGameCancel = false
            scoreOfFirstTeam = 0
            scoreOfSecondTeam = 0
            timeForTimer = TIME_CONST
            setupTeamsAndScoreTV()
        }
        // if button cancel was pressed //
        if (!isGameStart && isGameCancel) {
            setupTeamsAndScoreTV()
            setupButtonsAfterCancel()
        }
    }

    private fun setupTeamsAndScoreTV() {
        firstTeam = intent.getStringExtra("firstTeam").toString()
        secondTeam = intent.getStringExtra("secondTeam").toString()
        tvScoreOfFirstTeam.text = scoreOfFirstTeam.toString()
        tvScoreOfSecondTeam.text = scoreOfSecondTeam.toString()
        tvNameOfFirstTeam.text = firstTeam
        tvNameOfSecondTeam.text = secondTeam
    }

    private fun setupButtonsAfterCancel() {
        tvCountdownTimer.isVisible = false
        tvGameIsCanceled.isVisible = true
        btnAddPointToFirtsTeam.isClickable = false
        btnAddPointToSecondTeam.isClickable = false
        btnAddPointToFirtsTeam.isVisible = false
        btnAddPointToSecondTeam.isVisible = false
        btnCancelGame.isVisible = false
        btnCancelGame.isClickable = false
        btnGotoLOWFromSS.isVisible = true
        btnGotoMainFromSS.isVisible = true
        btnGotoLOWFromSS.isClickable = true
        btnGotoMainFromSS.isClickable = true
    }

    private fun setupButtomListener() {
        btnAddPointToFirtsTeam.setOnClickListener {
            scoreOfFirstTeam++
            tvScoreOfFirstTeam.text = scoreOfFirstTeam.toString()
        }
        btnAddPointToSecondTeam.setOnClickListener {
            scoreOfSecondTeam++
            tvScoreOfSecondTeam.text = scoreOfSecondTeam.toString()

        }
        btnCancelGame.setOnClickListener {
            isGameCancel = true
            countDownTimer.cancel()
            startGame()
        }
        btnGotoMainFromSS.setOnClickListener {
            MainActivity.start(this)
        }
        btnGotoLOWFromSS.setOnClickListener {
            ListOfWinnersActivity.start(this)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        isGameStart = false
        countDownTimer.cancel()
        MainActivity.start(this)

    }


    private fun startGame() {
        /// start the game///
        if (isGameStart && !isGameCancel) {
            btnAddPointToFirtsTeam.isClickable = true
            btnAddPointToSecondTeam.isClickable = true
            timeCalculation()
        }
        /// after counter stop
        if (!isGameStart && !isGameCancel && !isScoreComparisonDone) {
            setupButtonsAfterCancel()
            scoreComparison()
            isScoreComparisonDone = true
        }
        /// if cancel is pressed
        if (isGameStart && isGameCancel) {
            isGameStart = false
            countDownTimer.cancel()
            setupButtonsAfterCancel()
        }
    }

    private fun timeCalculation() {
        countDownTimer = object : CountDownTimer(timeForTimer, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                isTimerRun = true
                timeForTimer = millisUntilFinished
                tvCountdownTimer.text = "Timer:" + millisUntilFinished / 1000
                tvScoreOfFirstTeam.text = scoreOfFirstTeam.toString()
                tvScoreOfSecondTeam.text = scoreOfSecondTeam.toString()
            }

            override fun onFinish() {
                isTimerRun = false
                isGameStart = false
                tvCountdownTimer.text = "Timer: " + "is UP"
                startGame()
            }
        }.start()
    }

    private fun scoreComparison() {
        if (!isGameStart && !isGameCancel && !isTimerRun && !isScoreComparisonDone) {
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
        outState.putString("firstTeam", firstTeam)
        outState.putString("secondTeam", secondTeam)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        timeForTimer = savedInstanceState.getLong("timeForTimer")
        isGameStart = savedInstanceState.getBoolean("isGameStart")
        isGameCancel = savedInstanceState.getBoolean("isGameCancel")
        isTimerRun = savedInstanceState.getBoolean("isTimerRun")
        scoreOfFirstTeam = savedInstanceState.getInt("scoreOfFirstTeam")
        scoreOfSecondTeam = savedInstanceState.getInt("scoreOfSecondTeam")
        firstTeam = savedInstanceState.getString("firstTeam")
        secondTeam = savedInstanceState.getString("secondTeam")
        if (isGameStart) {
            tvScoreOfFirstTeam.text = scoreOfFirstTeam.toString()
            tvScoreOfSecondTeam.text = scoreOfSecondTeam.toString()
            tvNameOfFirstTeam.text = firstTeam
            tvNameOfSecondTeam.text = secondTeam
            timeCalculation()
        }
    }
}