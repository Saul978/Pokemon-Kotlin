package com.deloitte.base.domain.mappers

import com.deloitte.base.data.entities.DetailData
import com.deloitte.base.domain.entity.PokemonDetailEntity

class PokemonMapper {

    companion object {

        fun convert(model: DetailData) = with(model) {
            PokemonDetailEntity(
                sprites = sprites,
                stats = stats,
                height = height,
                weight = weight
            )
        }
    }
}
