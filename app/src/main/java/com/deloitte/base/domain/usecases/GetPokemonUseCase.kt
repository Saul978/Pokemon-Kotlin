package com.deloitte.base.domain.usecases

import com.deloitte.base.data.factory.ApiRepositoryFactory
import com.deloitte.base.domain.Strategy
import com.deloitte.base.domain.base.SingleUseCase
import com.deloitte.base.domain.entity.PokemonEntity
import javax.inject.Inject

class GetPokemonUseCase  @Inject constructor(private val apiRepositoryFactory: ApiRepositoryFactory) :
    SingleUseCase<List<PokemonEntity>>() {

    private lateinit var query: String
    private var limit: Int = 0
    private var offset: Int = 0

    override fun buildUseCase() = apiRepositoryFactory.create(Strategy.NETWORK).run {
        searchPokemon(limit, offset).map {
            it.results
        }
    }

    fun params(
        query: String,
        limit: Int,
        offset: Int
    ) =
        this.apply {
            this@GetPokemonUseCase.query = query
            this@GetPokemonUseCase.limit = limit
            this@GetPokemonUseCase.offset = offset
        }
}