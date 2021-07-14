package com.sylko.bestrecipes.generalviewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sylko.bestrecipes.pojo.Ingredients
import com.sylko.bestrecipes.pojo.Instructions

class ListIngredientsViewModel : ViewModel() {

    val ingredients = MutableLiveData<ArrayList<Ingredients>>()
    val instructions = MutableLiveData<ArrayList<Instructions>>()

    fun setIngredients(ingredients: ArrayList<Ingredients>) {
        this.ingredients.value = ingredients
    }

    fun setInstructions(instructions: ArrayList<Instructions>){
        this.instructions.value = instructions
    }

}