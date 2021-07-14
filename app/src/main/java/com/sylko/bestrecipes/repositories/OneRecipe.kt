package com.sylko.bestrecipes.repositories

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sylko.bestrecipes.pojo.Ingredients
import com.sylko.bestrecipes.pojo.Measure

class OneRecipe {

    private val db = Firebase.firestore
    private val ingredients = MutableLiveData<ArrayList<Ingredients>>()
    val textIngredients = MutableLiveData<String>()

    fun getData(id: String){
        db.collection("recipes")
            .document(id)
            .get()
            .addOnSuccessListener { result ->

                getIngredients(result)

            }
    }

    private fun getIngredients(doc: DocumentSnapshot){

        var sizeArrayIngredients = (doc.data?.get("ingredients") as ArrayList<HashMap<String, Any>>).size

        for (ingredient in 0 until sizeArrayIngredients) {
            var hashMapIngredients = (doc.data?.get("ingredients") as ArrayList<HashMap<String, Ingredients>>).get(ingredient)

            this.ingredients.value = hashMapIngredients.values.toList() as ArrayList<Ingredients>

            if (textIngredients.value == null) {
                this.textIngredients.value = hashMapIngredients["name"].toString() +
                        " " + hashMapIngredients["num"] +
                        " " + Measure.valueOf(hashMapIngredients["measure"].toString())
            }
            else {
                this.textIngredients.value = textIngredients.value +
                        "\n" + hashMapIngredients["name"] + " " + hashMapIngredients["num"] +
                        " " + Measure.valueOf(hashMapIngredients["measure"].toString())
            }
        }

    }

}