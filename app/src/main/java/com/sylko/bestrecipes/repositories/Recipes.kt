package com.sylko.bestrecipes.repositories

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sylko.bestrecipes.pojo.Recipe

class Recipes {

    private val db = Firebase.firestore
    var doc: ArrayList<Recipe>? = null
    val recipes = MutableLiveData<ArrayList<Recipe>>()

    init {
        doc = ArrayList()
        db.collection("recipes")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Чтение рецепта ", "${document.id} => ${document.data["name"]}")
                    //записываю каждый рецепт в массив рецептов
                    //а можно записывать в локальную бд, а с нее уже с помощью пагинации доставать
                    doc?.add(Recipe(name = document.data["name"].toString(), id = document.id))
//                    //вместо  записи поштучно лучше использовать пагинацию
//                    this.recipes.value = doc
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }
            .addOnCompleteListener {
                //кладу заполненный массив в MutableLiveData
                //здесь загружаются сразу все рецепты, что может привести к длительному ожиданию
                //поэтому либо нужно добавить пагинацию, либо писать поштучно в addOnSuccessListener
                this.recipes.value = doc
            }
    }
}