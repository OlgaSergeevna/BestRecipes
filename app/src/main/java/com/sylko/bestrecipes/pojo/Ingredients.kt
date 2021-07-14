package com.sylko.bestrecipes.pojo

data class Ingredients(

    /**
     * Наименование
     */
    val name: String,

    /**
     * Количество
     */
    val num: Double,

    /**
     * Единица измерения
     */
    val measure: Measure
)
