package com.example.shymko_firstproject.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shymko_firstproject.R
import com.example.shymko_firstproject.Winner
import com.example.shymko_firstproject.WinnerAdapter
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
        val sortedListOfWinner = Winner.listOfWinner.sortedByDescending { it -> it.score }

        val adapter = WinnerAdapter(sortedListOfWinner)
        rvListOfWinners.adapter = adapter
        rvListOfWinners.layoutManager = LinearLayoutManager(this)
        adapter.notifyItemInserted(sortedListOfWinner.size - 1)
        setupButtonListeners()

    }

    private fun setupButtonListeners() {
        btnGotoMainFromLOW.setOnClickListener {
            MainActivity.start(this)
        }
        btnClearListOfWinners.setOnClickListener {
            clearListOfWinner()
        }
    }

    private fun clearListOfWinner() {
        Winner.listOfWinner.clear()
        ListOfWinnersActivity.start(this)
    }

}