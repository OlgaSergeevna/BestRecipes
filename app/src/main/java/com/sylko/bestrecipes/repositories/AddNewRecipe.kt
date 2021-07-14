package com.sylko.bestrecipes.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sylko.bestrecipes.pojo.Ingredients
import com.sylko.bestrecipes.pojo.Instructions
import com.sylko.bestrecipes.util.Event
import java.time.LocalDate

class AddNewRecipe {

    //для вывода событий
    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get() = statusMessage
    //флаг успешной записи
    var error = MutableLiveData(true)

    //bd firebase
    private val db = Firebase.firestore

    fun addData(
        type: String,
        name: String,
        ingredients: ArrayList<Ingredients>
       // instructions: ArrayList<Instructions>
    ) {
        addRecipe(type, name, ingredients)
    }

    private fun addRecipe(type: String, name: String, ingredients: ArrayList<Ingredients>){

        val recipe = hashMapOf(
            "type" to type,
            "name" to name,
            "date" to LocalDate.now().toString(),
            "ingredients" to ingredients
        )
        db.collection("recipes")
            .add(recipe)
            .addOnSuccessListener {
                //добавляю ингредиенты во вложенную коллекцию
                //addIngredients(ingredients, it.id)

                statusMessage.value = Event("Рецепт успешно добавлен")
                error.value = false
            }
            .addOnFailureListener { e ->
                statusMessage.value = Event("Ошибка записи рецепта: ${e.message}")
                error.value = true
            }
    }

    //добавление вложенной таблицы ингредиентов
//    private fun addIngredients(ingredients: ArrayList<Ingredients>, parent: String){
//
//        ingredients.forEach {
//            val ingredient = hashMapOf(
//                "name" to it.name,
//                "num" to it.num,
//                "measure" to it.measure
//            )
//            db.collection("recipes").document(parent)
//                .collection("ingredients")
//                .add(ingredient)
//                .addOnSuccessListener {
//                    //statusMessage.value = Event("Ингредиенты успешно добавлен")
//                    //error.value = false
//                }
//                .addOnFailureListener { e ->
//                    //statusMessage.value = Event("Ошибка записи рецепта: ${e.message}")
//                    //error.value = true
//                }
//
//        }
//    }

    //здесь добавляем таблицы инструкций
//    private fun addInstructions(instructions: ArrayList<Instructions>){
//
//    }


}