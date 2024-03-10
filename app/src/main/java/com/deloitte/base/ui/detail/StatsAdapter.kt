package com.deloitte.base.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deloitte.base.R
import dev.ronnie.pokeapiandroidtask.model.Stats

class StatsAdapter(private val stats: List<Stats>) :RecyclerView.Adapter<StatsAdapter.StatsViewHolder>() {

    inner class StatsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StatsViewHolder {
        return StatsViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.view_item_stats,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return stats.size
    }

    override fun onBindViewHolder(p0: StatsViewHolder, p1: Int) {
        val pokemonItemView = stats[p1]

        p0.itemView.apply {
            findViewById<ProgressBar>(R.id.stats_bar).progress= pokemonItemView.base_stat
            findViewById<TextView>(R.id.status_textview).text = pokemonItemView.stat.name
            findViewById<TextView>(R.id.percentage_textview).text = "${pokemonItemView.base_stat}/300"
        }

    }



}