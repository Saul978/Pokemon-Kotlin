package com.deloitte.base.ui.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.deloitte.base.R
import com.deloitte.base.databinding.FragmentListBinding
import com.deloitte.base.domain.entity.PokemonEntity
import com.deloitte.base.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter
    private val model by viewModels<ListViewModel>()

    override fun getViewBinding() = FragmentListBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        setupRecycler()

        initObservers()


        val searchView =  binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let { model.filterQuery(it) }
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                p0?.let { model.filterQuery(it) }
                return false
            }
        })
        pokemonAdapter.setOnItemClickListener { pokemon ->
           newFragment(pokemon)
        }
    }

    fun initObservers(){
        model.isLoading.observe(viewLifecycleOwner){
            if(it)binding.loading.visibility=View.VISIBLE else binding.loading.visibility=View.GONE
        }
        binding.Backtotop.setOnClickListener { backtoTop() }
        model.pokemonList.observe(viewLifecycleOwner) { pokemonAdapter.diff.submitList(it) }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy>5){
                    binding.Backtotop.visibility= View.VISIBLE
                }else if(dy<0){
                    binding.Backtotop.visibility= View.GONE
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun setupRecycler(){
        pokemonAdapter = PokemonAdapter(model)
        recyclerView = binding.pokemonList
        recyclerView.apply {
            adapter= pokemonAdapter

        }

    }
    private fun backtoTop(){
        recyclerView.smoothScrollToPosition(0)
    }

    private fun newFragment(pokemon:PokemonEntity){
        val bundle = Bundle()
        bundle.putString("name",pokemon.name)
        bundle.putString("url",pokemon.url)

        val detailFragment = DetailFragment().apply {
            arguments = bundle
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.navigation_host,detailFragment)
            .addToBackStack(null)
            .commit()
    }
}