package com.deloitte.base.domain.usecases

import com.deloitte.base.data.factory.ApiRepositoryFactory
import com.deloitte.base.domain.Strategy
import com.deloitte.base.domain.base.SingleUseCase
import com.deloitte.base.domain.entity.PokemonDetailEntity
import com.deloitte.base.domain.mappers.PokemonMapper
import javax.inject.Inject
import kotlin.properties.Delegates

class GetDetailPokemonUseCase  @Inject constructor(private val apiRepositoryFactory: ApiRepositoryFactory) :
    SingleUseCase<PokemonDetailEntity>() {

    private var id by Delegates.notNull<Int>()

    override fun buildUseCase() = apiRepositoryFactory.create(Strategy.NETWORK).run {
        getPokemonById(id).map{
            PokemonMapper.convert(it)
        }
    }
    fun params(
        id: Int

    ) =
        this.apply {
            this@GetDetailPokemonUseCase.id = id
        }


}