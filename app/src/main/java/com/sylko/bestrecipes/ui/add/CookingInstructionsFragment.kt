package com.sylko.bestrecipes.ui.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sylko.bestrecipes.R
import com.sylko.bestrecipes.adapters.InstructionsAdapter
import com.sylko.bestrecipes.databinding.FragmentCookingInstructionsBinding
import com.sylko.bestrecipes.pojo.Instructions
import com.sylko.bestrecipes.generalviewmodels.ListIngredientsViewModel

class CookingInstructionsFragment : Fragment(R.layout.fragment_cooking_instructions) {

    private lateinit var binding: FragmentCookingInstructionsBinding
    private lateinit var rv: RecyclerView
    private var adapter: InstructionsAdapter? = null
    private lateinit var listViewModel: ListIngredientsViewModel

    private lateinit var instructions: ArrayList<Instructions>
    private lateinit var textInstruction: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instructions = ArrayList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCookingInstructionsBinding.bind(view)

        //здесь я привязала ViewModel к активити, чтобы она была доступна из различных фрагментов
        listViewModel = ViewModelProvider(requireActivity()).get(ListIngredientsViewModel::class.java)

        initRecycler()

        binding.btnAddInstruction.setOnClickListener {
            addInstruction()
        }

        binding.btnSaveInstructions.setOnClickListener {
            listViewModel.setInstructions(instructions)

            activity?.onBackPressed()
        }

        //здесь заполняю ингредиенты, если список уже был заполнен
        listViewModel.instructions.observe(viewLifecycleOwner, Observer {
            instructions = it
            initAdapter()
        })

    }

    private fun initAdapter(){
        adapter = InstructionsAdapter(instructions, this)
        rv.adapter = adapter
        adapter?.itemCount?.minus(1)?.let { rv.scrollToPosition(it) }
    }

    private fun initRecycler(){
        rv = binding.rvInstructions
        rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun addInstruction(){
        textInstruction = binding.etInstruction.text.toString().trim()
        if (textInstruction.isNotEmpty()){
            instructions.add(Instructions(textInstruction))
            binding.etInstruction.setText("")
        }
        else{
            Toast.makeText(context, "Поле инструкции не может быть пустым!", Toast.LENGTH_LONG).show()
        }
        initAdapter()
    }

}