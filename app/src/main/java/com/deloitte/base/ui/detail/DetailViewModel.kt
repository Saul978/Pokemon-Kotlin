package com.deloitte.base.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deloitte.base.data.api.LikeDAO
import com.deloitte.base.domain.entity.Likes
import com.deloitte.base.domain.entity.PokemonDetailEntity
import com.deloitte.base.domain.usecases.GetDetailPokemonUseCase
import com.deloitte.base.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getDetailPokemonUseCase: GetDetailPokemonUseCase, private val likeDAO: LikeDAO): BaseViewModel() {

    private val _pokemontLiveData= MutableLiveData<PokemonDetailEntity>()
    val pokemon: LiveData<PokemonDetailEntity> = _pokemontLiveData


     fun likePokemon(id:Int){
        likeDAO.insert(Likes(id))
    }
     fun dislikePokemon(id:Int){
        likeDAO.delete(Likes(id))
    }
     fun isLiked(id:Int):Boolean{
        return likeDAO.getLike(id) != null
    }
    fun getPokemonDetail(id:Int){
            getDetailPokemonUseCase.params(id).execute(
                onSuccess = {pokemonDetail -> _pokemontLiveData.value= pokemonDetail},
                onError = {error ->}
            )
    }

}
