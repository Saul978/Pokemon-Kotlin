package com.deloitte.base.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonEntity(
    val name: String,
    val url: String
):Parcelable