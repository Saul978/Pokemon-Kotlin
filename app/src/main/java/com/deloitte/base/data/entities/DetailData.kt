package com.deloitte.base.data.entities

import android.os.Parcelable
import dev.ronnie.pokeapiandroidtask.model.Sprites
import dev.ronnie.pokeapiandroidtask.model.Stats
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailData(
    val sprites: Sprites,
    val stats: List<Stats>,
    val height: Int,
    val weight: Int
) : Parcelable