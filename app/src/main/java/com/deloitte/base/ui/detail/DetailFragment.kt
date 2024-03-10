package com.deloitte.base.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deloitte.base.R
import com.deloitte.base.databinding.FragmentDetailBinding
import com.deloitte.base.ui.base.BaseFragment
import com.deloitte.base.ui.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.ronnie.pokeapiandroidtask.model.Stats

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var statsAdapter: StatsAdapter
    private lateinit var spritesList: List<String>
    private var idPokemon: Int = 0
    private var SpriteIndex = 0


    private val model by viewModels<DetailViewModel>()
    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = arguments?.getString("url")
        idPokemon = url?.split("/".toRegex())?.dropLast(1)?.last()?.toInt() ?: 0
        idPokemon?.let { model.getPokemonDetail(it) }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding.materialToolbar2.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }



    fun initObservers(){
        model.pokemon.observe(viewLifecycleOwner) {
            binding.textViewNumber.text= "${getViewBinding().textViewNumber.text} ${idPokemon} "

            binding.textView2.text = arguments?.getString("name")
            binding.textViewWeight.text = "${getViewBinding().textViewWeight.text} ${it.height} kgs"
            binding.textViewHeight.text = "${getViewBinding().textViewHeight.text} ${(it.height.toFloat()/10)} m"
            spritesList = listOf(it.sprites.front_default,
                it.sprites.back_default,
                it.sprites.front_shiny,
                it.sprites.back_shiny)
            Glide.with(this).load(spritesList[SpriteIndex]).into(binding.pokemonImageDetail)
            binding.Heart.setImageResource(if (model.isLiked(idPokemon) )R.drawable.heart_filled else R.drawable.heart)
            setupRecycler(it.stats)
        }
        binding.Heart.setOnClickListener{onHeartClick()}
        binding.pokemonImageDetail.setOnClickListener{changeSprite()}
    }

    private fun setupRecycler(stats:List<Stats>){
        statsAdapter = StatsAdapter(stats)
        recyclerView = binding.statsList
        recyclerView.apply {
            adapter= statsAdapter

        }

    }

    private fun changeSprite(){
        SpriteIndex= (SpriteIndex +1 )% spritesList.size
        Glide.with(this).load(spritesList[SpriteIndex]).into(binding.pokemonImageDetail)

    }

    private fun onHeartClick(){
        if(model.isLiked(idPokemon)){
            model.dislikePokemon(idPokemon)
            binding.Heart.setImageResource(R.drawable.heart)
        }else{
            model.likePokemon(idPokemon)
            binding.Heart.setImageResource(R.drawable.heart_filled)
        }
    }



}