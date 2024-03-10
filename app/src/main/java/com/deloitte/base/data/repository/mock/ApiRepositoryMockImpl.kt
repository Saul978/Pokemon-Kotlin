package com.deloitte.base.data.repository.mock

import com.deloitte.base.data.entities.DetailData
import com.deloitte.base.domain.repository.ApiRepository
import com.example.pokemonapp.network.response.SearchData
import io.reactivex.Single

class ApiRepositoryMockImpl: ApiRepository {

    override fun searchPokemon(limit: Int, offset: Int): Single<SearchData> {
        TODO("Not yet implemented")
    }

    override fun getPokemonById(pokemonId: Int): Single<DetailData> {
        TODO("Not yet implemented")
    }



}