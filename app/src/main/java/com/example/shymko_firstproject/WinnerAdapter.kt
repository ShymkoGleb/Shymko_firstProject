package com.example.shymko_firstproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WinnerAdapter(
    var winner: List<Winner>
) : RecyclerView.Adapter<WinnerAdapter.PersonViewHolder>() {
    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler, parent, false)
        return PersonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return winner.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.itemView.apply {
           // tvFirstName.text = winner[position].fullName
            //  tvSecondName.text = person[position].secondName
        }
    }
}