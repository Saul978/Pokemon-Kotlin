package com.deloitte.base.domain.entity

import android.os.Parcelable
import dev.ronnie.pokeapiandroidtask.model.Sprites
import dev.ronnie.pokeapiandroidtask.model.Stats
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailEntity(
    val sprites: Sprites,
    val stats: List<Stats>,
    val height: Int,
    val weight: Int
) : Parcelable