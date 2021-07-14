package com.sylko.bestrecipes.pojo

data class Recipe (

    /**
     * Тип
     */
    val type: TypeOfMeal? = null,

    /**
     * Название рецепта
     */
    var name: String,

    /**
     * Ингредиенты
     */
    val ingredients: Ingredients? = null,

    /**
     * Текст рецепта
     */
    val text: String? = null,

    /**
     * id
     */
    val id: String? = null

    )