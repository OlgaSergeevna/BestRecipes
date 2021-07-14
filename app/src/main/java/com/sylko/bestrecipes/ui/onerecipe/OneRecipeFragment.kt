package com.sylko.bestrecipes.ui.onerecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sylko.bestrecipes.R
import com.sylko.bestrecipes.databinding.FragmentOneRecipeBinding
import com.sylko.bestrecipes.generalviewmodels.OneRecipeViewModel

class OneRecipeFragment: Fragment(R.layout.fragment_one_recipe) {

    private lateinit var binding: FragmentOneRecipeBinding
    private lateinit var viewModel: OneRecipeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOneRecipeBinding.bind(view)

        viewModel.textIngredients.observe(viewLifecycleOwner, {
            binding.tvIngredientsOneRecipe.text = it
        })

        binding.tvNameOneRecipe.text = "khgjh"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //здесь я привязала ViewModel к активити, чтобы она была доступна из различных фрагментов
        viewModel = ViewModelProvider(requireActivity()).get(OneRecipeViewModel::class.java)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        //здесь обнулила ингредиенты из открытого ранее рецепта
        viewModel.textIngredients.value = null
    }

}