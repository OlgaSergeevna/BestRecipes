package com.sylko.bestrecipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sylko.bestrecipes.R
import com.sylko.bestrecipes.databinding.OneIngredientBinding
import com.sylko.bestrecipes.pojo.Ingredients
import com.sylko.bestrecipes.ui.add.ListOfIngredientsFragment

class IngredientsAdapter(
        private val ingredientsList: List<Ingredients>,
        private val listener: ListOfIngredientsFragment
) :
        RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_ingredient, parent, false)

        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredientsList = ingredientsList[position]

        holder.name.text = ingredientsList.name
        holder.num.text = ingredientsList.num.toString()
        holder.measure.text = ingredientsList.measure.toString()
    }

    override fun getItemCount() = ingredientsList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private val binding = OneIngredientBinding.bind(view)

        val name = binding.tvNameIngredient
        val num = binding.tvNumIngredient
        val measure = binding.tvMeasureIngredient
        private val delete = binding.btnDelete

        init {
            delete.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
//                listener.onItemClick(position)
            }
        }
    }

//    fun getId(position: Int): String {
//        return saleList[position].uid
//    }
//
//    fun getName(position: Int): String{
//        return saleList[position].name
//    }
//
//    fun getCost(position: Int): Double{
//        return saleList[position].cost
//    }
//
//    fun getNum(position: Int): Int{
//        return saleList[position].num
//    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}
