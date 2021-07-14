package com.sylko.bestrecipes.ui.recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sylko.bestrecipes.pojo.Recipe
import com.sylko.bestrecipes.repositories.Recipes

class RecipesViewModel: ViewModel() {

    var repository: Recipes? = null
    var recipes = MutableLiveData<ArrayList<Recipe>>()

    init {
        repository = Recipes()
        recipes = repository?.recipes!!
    }

}