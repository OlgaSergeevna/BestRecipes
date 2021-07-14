package com.sylko.bestrecipes.pojo

enum class Measure(private val value: String) {

    KILOGRAM("кг."),
    GRAM("гр."),
    TABLESPOON("ст.ложка"),
    TEASPOON("ч.ложка");

    override fun toString() : String {
        return value
    }

}