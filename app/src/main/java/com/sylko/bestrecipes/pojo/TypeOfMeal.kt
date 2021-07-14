package com.sylko.bestrecipes.pojo

enum class TypeOfMeal(private val value: String) {

    BAKE("Выпечка"),
    SALAD("Салат"),
    CAKE("Торт");

    override fun toString() : String {
        return value
    }

}