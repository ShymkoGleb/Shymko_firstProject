package com.example.shymko_firstproject.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.shymko_firstproject.R
import com.example.shymko_firstproject.Winner
import kotlinx.android.synthetic.main.activity_score_screen.*
import kotlinx.android.synthetic.main.activity_winner_screen.*

class ScoreScreenActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ScoreScreenActivity::class.java)
            context.startActivity(intent)
        }

        var startGame = true
        var cancelGame = false
        var scoreOfFirstTeam: Int = 0
        var scoreOfSecondTeam: Int = 0

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_screen)
        startGame = true
        cancelGame = false
        scoreOfFirstTeam = 0
        scoreOfSecondTeam = 0
        tvScoreOfFirstTeam.text = scoreOfFirstTeam.toString()
        tvScoreOfSecondTeam.text = scoreOfSecondTeam.toString()
        Log.d("CustomDebug", "OnCreate 1st, startGame= $startGame")
        val firstTeam: String = intent.getStringExtra("firstTeam").toString()
        val secondTeam: String = intent.getStringExtra("secondTeam").toString()
        tvNameOfFirstTeam.text = firstTeam
        tvNameOfSecondTeam.text = secondTeam
        Log.d(
            "CustomDebug",
            "OnCreate 1st, firstTeamCompanion= $firstTeam,  secondTeamCompanion= $secondTeam"
        )
        onStart()
    }

    override fun onStart() {
        super.onStart()
        if (startGame == true) {
            btnAddPointToFirtsTeam.isClickable = true
            btnAddPointToSecondTeam.isClickable = true
            timeCalculation()
            Log.d("CustomDebug", "OnStart 1st, startGame= $startGame")
            btnAddPointToFirtsTeam.setOnClickListener {
                scoreOfFirstTeam++
            }
            btnAddPointToSecondTeam.setOnClickListener {
                scoreOfSecondTeam++
            }
            btnCancelGame.setOnClickListener {
                startGame = false
                cancelGame = true
            }
        } else {
            btnAddPointToFirtsTeam.isClickable = false
            btnAddPointToSecondTeam.isClickable = false
            btnAddPointToFirtsTeam.isVisible = false
            btnAddPointToSecondTeam.isVisible = false
            Toast.makeText(this, "Game is over", Toast.LENGTH_SHORT)
            if (!cancelGame && !startGame) {
                btnCancelGame.isVisible = false
                scoreComorison()
            }

        }
    }

    fun timeCalculation() {
        object : CountDownTimer(4000, 1000) {
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

    fun scoreComorison() {
        val firstTeam: String = intent.getStringExtra("firstTeam").toString()
        if (scoreOfFirstTeam > scoreOfSecondTeam) {
            Winner.listOfWinner.add(Winner(firstTeam, scoreOfFirstTeam))
            val intent = Intent(this, WinnerScreenActivity::class.java)
            intent.putExtra("winner", firstTeam)
            intent.putExtra("score", scoreOfFirstTeam.toString())


            Log.d("CustomDebug", "scoreComorison scoreOfFirstTeam > scoreOfSecondTeam")
            startActivity(intent)
        }
        if (scoreOfFirstTeam < scoreOfSecondTeam) {
            val secondTeam: String = intent.getStringExtra("secondTeam").toString()
            Winner.listOfWinner.add(Winner(secondTeam, scoreOfSecondTeam))
            Log.d("CustomDebug", "scoreComorison scoreOfFirstTeam < scoreOfSecondTeam")
            val intent = Intent(this, WinnerScreenActivity::class.java)
            intent.putExtra("winner", secondTeam)
            intent.putExtra("score", scoreOfSecondTeam.toString())
            startActivity(intent)

        } else {
            val intent = Intent(this, WinnerScreenActivity::class.java)
            intent.putExtra("winner", "No winner in this game")
            intent.putExtra("score", "The score is tie")
            startActivity(intent)
        }
    }
}
