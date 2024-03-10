package com.deloitte.base.data.repository.network

import com.deloitte.base.data.api.Api
import com.deloitte.base.data.entities.DetailData
import com.deloitte.base.domain.repository.ApiRepository
import com.example.pokemonapp.network.response.SearchData
import io.reactivex.Single

class ApiRepositoryNetworkImpl(private val api: Api) : ApiRepository {

    override fun searchPokemon(limit: Int, offset: Int): Single<SearchData> =
        api.searchPokemon(limit, offset)

    override fun getPokemonById(pokemonId: Int): Single<DetailData> =
        api.getPokemonById(pokemonId)

}