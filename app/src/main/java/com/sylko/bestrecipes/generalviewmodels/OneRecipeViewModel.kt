package com.sylko.bestrecipes.generalviewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sylko.bestrecipes.repositories.OneRecipe

class OneRecipeViewModel: ViewModel() {

    var repository: OneRecipe? = null
    var textIngredients = MutableLiveData<String>()

    init {
        repository = OneRecipe()
    }

    fun getData(
        id: String
    ) {
        repository?.getData(id)
        textIngredients = repository?.textIngredients!!
    }

}