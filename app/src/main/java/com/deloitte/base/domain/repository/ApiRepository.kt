package com.deloitte.base.domain.repository

import com.deloitte.base.data.entities.DetailData
import com.example.pokemonapp.network.response.SearchData
import io.reactivex.Single

interface ApiRepository {

    fun searchPokemon(limit: Int, offset: Int): Single<SearchData>
    fun getPokemonById(pokemonId: Int): Single<DetailData>

}