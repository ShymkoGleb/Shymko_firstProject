package com.example.shymko_firstproject

class Winner (
    var name:String
        ){
    val winnerList = mutableListOf<Winner>()
    val adapter = WinnerAdapter(winnerList)
}