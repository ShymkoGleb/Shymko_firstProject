package com.example.shymko_firstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*Game score app:
- app consists of 4 screens: enter 2 teams screen, score screen, winner screen, list of winners screen
- score screen has count down timer for the game and 2 buttons for each team to increment the score
- user can cancel thegame
- each winner should be saved to the list of winners after the game (within the app session)
- user can review list of winners sorted by score in descending order
- user can clear the list of winners
- user can share results of the game*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }



}