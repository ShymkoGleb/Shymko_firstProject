package com.example.shymko_firstproject

class Winner (
    var name:String,
    var score:Int
        ){
    companion object{
        val listOfWinner = mutableListOf<Winner>()
        val sortedListOfWinner = listOfWinner.sortedByDescending { listOfWinner-> listOfWinner.score }
    }
}