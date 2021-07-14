package com.sylko.bestrecipes.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sylko.bestrecipes.pojo.Ingredients
import com.sylko.bestrecipes.pojo.Instructions
import com.sylko.bestrecipes.repositories.AddNewRecipe
import com.sylko.bestrecipes.util.Event

class AddNewRecipeViewModel: ViewModel() {

    var repository: AddNewRecipe? = null
    var error: MutableLiveData<Boolean>? = null
    var message : LiveData<Event<String>>? = null

    init {
        repository = AddNewRecipe()
        error = repository?.error
        message = repository?.message
    }

    fun addData(
        type: String,
        name: String,
        ingredients: ArrayList<Ingredients>
        //instructions: ArrayList<Instructions>
    ): Unit? {
        return repository?.addData(type, name, ingredients)
    }

}