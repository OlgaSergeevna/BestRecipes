package com.sylko.bestrecipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sylko.bestrecipes.R
import com.sylko.bestrecipes.databinding.OneRecipeBinding
import com.sylko.bestrecipes.pojo.Recipe

class RecipesAdapter(
        private  val recipesList: List<Recipe>,
        private val listener: OnItemClickListener
) :
        RecyclerView.Adapter<RecipesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_recipe, parent, false)

        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRecipe = recipesList[position]

        holder.name.text = currentRecipe.name
    }

    override fun getItemCount() = recipesList.size

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private val binding = OneRecipeBinding.bind(view)

        val name = binding.tvNameRecipe
        private val cv = binding.root

        init {
            cv.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    fun getId(position: Int): String? {
        return recipesList[position].id
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}
