package com.sylko.bestrecipes.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sylko.bestrecipes.R
import com.sylko.bestrecipes.adapters.RecipesAdapter
import com.sylko.bestrecipes.databinding.FragmentRecipesBinding
import com.sylko.bestrecipes.generalviewmodels.OneRecipeViewModel

class RecipesFragment : Fragment(R.layout.fragment_recipes), RecipesAdapter.OnItemClickListener {

    private lateinit var viewModel: RecipesViewModel
    private lateinit var oneRecipeViewModel: OneRecipeViewModel
    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recyclerView: RecyclerView
    private var adapter: RecipesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecipesBinding.bind(view)

        recyclerView = binding.rvRecipes

        initView()
        initAdapter()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)
        //здесь я привязала ViewModel к активити, чтобы она была доступна из различных фрагментов
        oneRecipeViewModel = ViewModelProvider(requireActivity()).get(OneRecipeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_recipes, container, false)
        return root
    }

    private fun initView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun initAdapter() {
        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            adapter = RecipesAdapter(it, this)
            recyclerView.adapter = adapter
        })
    }

    override fun onItemClick(position: Int) {
        //получила  id выбранного рецепта
        var id = adapter?.getId(position)
        if (id != null) {
            oneRecipeViewModel.getData(id)
        }

        NavHostFragment.findNavController(this).navigate(R.id.action_nav_recipes_to_oneRecipeFragment)
    }
}