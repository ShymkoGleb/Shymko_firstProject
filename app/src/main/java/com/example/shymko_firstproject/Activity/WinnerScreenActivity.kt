package com.example.shymko_firstproject.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shymko_firstproject.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_score_screen.*
import kotlinx.android.synthetic.main.activity_winner_screen.*

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
        val winner: String? = intent.getStringExtra("winner")
        val score: String? = intent.getStringExtra("score")
        tvWinner.text = winner
        tvScore.text = score

    setupButtomListener()


    }

    fun setupButtomListener(){
        btnGotoMainFromWS.setOnClickListener {
            MainActivity.start(this)
        }
        btnGotoLOWFromWS.setOnClickListener {
            ListOfWinnersActivity.start(this)
        }
        btnShareResult.setOnClickListener {
            shareResult()
        }
    }

    fun shareResult(){
        val winner: String? = intent.getStringExtra("winner")
        val score: String? = intent.getStringExtra("score")
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "The winner of this mutch is $winner, with the score:$score")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}