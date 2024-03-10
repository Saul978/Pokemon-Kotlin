package com.deloitte.base.data.api

import com.deloitte.base.data.entities.DetailData
import com.example.pokemonapp.network.response.SearchData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("pokemon/")
    fun searchPokemon(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): Single<SearchData>

    @GET("pokemon/{id}/")
    fun getPokemonById(
        @Path("id") pokemonId: Int
    ): Single<DetailData>

}