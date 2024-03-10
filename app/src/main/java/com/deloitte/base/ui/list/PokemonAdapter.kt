package com.deloitte.base.ui.list

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.deloitte.base.R
import com.deloitte.base.domain.entity.PokemonEntity

class PokemonAdapter(private val viewModel: ListViewModel) :RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    private val differCallback = object: DiffUtil.ItemCallback<PokemonEntity>(){
        override fun areItemsTheSame(p0: PokemonEntity, p1: PokemonEntity): Boolean {
            return p0==p1
        }

        override fun areContentsTheSame(p0: PokemonEntity, p1: PokemonEntity): Boolean {
            return p0==p1
        }

    }

    val diff = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PokemonViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.view_item_pokemon,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }

    override fun onBindViewHolder(p0: PokemonViewHolder, p1: Int) {
        val pokemonItemView = diff.currentList[p1]

        p0.itemView.apply {
            val pokemonImage = viewModel.getPokemonsImages(diff.currentList[p1].url)
            Glide.with(this).load(pokemonImage).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(
                    p0: Drawable?,
                    p1: Any?,
                    p2: Target<Drawable>?,
                    p3: DataSource?,
                    p4: Boolean
                ): Boolean {
                    findViewById<ProgressBar>(R.id.progress_circular).visibility = View.GONE
                    return false
                }

            }).into(findViewById(R.id.pokemon_item_image))
            findViewById<TextView>(R.id.pokemon_item_title).text = pokemonItemView.name

            setOnClickListener{
                onItemClickListener?.invoke(pokemonItemView)
            }

        }
    }

    private var onItemClickListener : ((PokemonEntity)-> Unit)? = null

    fun setOnItemClickListener(listener: (PokemonEntity)-> Unit){
        onItemClickListener = listener

    }


}