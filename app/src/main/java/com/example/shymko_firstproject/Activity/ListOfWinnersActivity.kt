package com.example.shymko_firstproject.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val adapter = WinnerAdapter(Winner.listOfWinner)
        rvListOfWinners.adapter = adapter
        rvListOfWinners.layoutManager = LinearLayoutManager(this)
        adapter.notifyItemInserted(Winner.listOfWinner.size - 1)


        btnGotoMainFromLOW.setOnClickListener{
            MainActivity.start(this)
        }
    }
}