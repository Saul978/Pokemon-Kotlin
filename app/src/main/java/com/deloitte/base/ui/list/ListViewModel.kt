package com.deloitte.base.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.deloitte.base.domain.entity.PokemonEntity
import com.deloitte.base.domain.usecases.GetPokemonUseCase
import com.deloitte.base.ui.base.BaseViewModel
import com.deloitte.base.utils.extractId
import com.deloitte.base.utils.getPicUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getPokemonUseCase: GetPokemonUseCase): BaseViewModel() {
    private var original = mutableListOf<PokemonEntity>()
    private val _pokemonListLivaData= MutableLiveData<List<PokemonEntity>>()
    val pokemonList: LiveData<List<PokemonEntity>> = _pokemonListLivaData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    init {
        getPokemons()
    }
    fun getPokemons() = viewModelScope.launch {
        _isLoading.value = true
         getPokemonUseCase.params(limit = 10000, query = " ", offset = 0).execute(
            onSuccess = {pokemonList -> _pokemonListLivaData.value = pokemonList
                        original = pokemonList.toMutableList()
                        _isLoading.value = false},
            onError = {error ->}
        )
    }

    fun getPokemonsImages(url:String) : String {
        return url.getPicUrl()
    }

    fun filterQuery(query:String){
            val filtered = original.filter { it.name.lowercase().contains(query.lowercase()) }
            _pokemonListLivaData.value = filtered
    }
}