package com.sylko.bestrecipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sylko.bestrecipes.R
import com.sylko.bestrecipes.databinding.OneInstructionBinding
import com.sylko.bestrecipes.pojo.Instructions
import com.sylko.bestrecipes.ui.add.CookingInstructionsFragment

class InstructionsAdapter (
    private val instructionsList: List<Instructions>,
    private val listener: CookingInstructionsFragment
) :
    RecyclerView.Adapter<InstructionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_instruction, parent, false)

        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val instructionsList = instructionsList[position]

        holder.instruction.text = instructionsList.instruction
       // holder.photo = instructionsList.photo

//        holder.name.text = ingredientsList.name
//        holder.num.text = ingredientsList.num.toString()
//        holder.measure.text = ingredientsList.measure.toString()
    }

    override fun getItemCount() = instructionsList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private val binding = OneInstructionBinding.bind(view)

        val instruction = binding.tvTextInstruction
        //var photo = binding.ivPhotoInstruction

//        val name = binding.tvNameIngredient
//        val num = binding.tvNumIngredient
//        val measure = binding.tvMeasureIngredient
//        private val delete = binding.btnDelete

//        init {
//            delete.setOnClickListener(this)
//        }

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
