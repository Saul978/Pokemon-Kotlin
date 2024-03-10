package com.example.pokemonapp.network.response

import android.os.Parcelable
import com.deloitte.base.domain.entity.PokemonEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchData(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonEntity>
) : Parcelable
