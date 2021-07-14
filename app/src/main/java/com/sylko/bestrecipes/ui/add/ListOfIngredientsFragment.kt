package com.sylko.bestrecipes.ui.add

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sylko.bestrecipes.R
import com.sylko.bestrecipes.adapters.IngredientsAdapter
import com.sylko.bestrecipes.databinding.FragmentListOfIngredientsBinding
import com.sylko.bestrecipes.pojo.Ingredients
import com.sylko.bestrecipes.pojo.Measure
import com.sylko.bestrecipes.util.DecimalDigitsInputFilter
import com.sylko.bestrecipes.generalviewmodels.ListIngredientsViewModel

class ListOfIngredientsFragment : Fragment(R.layout.fragment_list_of_ingredients) {

    private lateinit var binding: FragmentListOfIngredientsBinding
    private lateinit var rv: RecyclerView
    private var adapter: IngredientsAdapter? = null
    private lateinit var listViewModel: ListIngredientsViewModel

    private lateinit var ingredients: ArrayList<Ingredients>
    private lateinit var textIngredient: String
    private lateinit var textNum: String
    private lateinit var textMeasure: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ingredients = ArrayList()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListOfIngredientsBinding.bind(view)
        //здесь я привязала ViewModel к активити, чтобы она была доступна из различных фрагментов
        listViewModel = ViewModelProvider(requireActivity()).get(ListIngredientsViewModel::class.java)

        inputFilters()
        initRecycler()

        binding.spinnerMeasure.adapter = ArrayAdapter(
                this.requireContext(), android.R.layout.simple_spinner_dropdown_item, Measure.values()
        )

        binding.btnSaveIngredients.setOnClickListener {

            listViewModel.setIngredients(ingredients)

            activity?.onBackPressed()
        }

        binding.btnAdd.setOnClickListener{
            addIngredient()
        }

        //здесь заполняю ингредиенты, если список уже был заполнен
        listViewModel.ingredients.observe(viewLifecycleOwner, Observer {
            ingredients = it
            initAdapter()
        })

    }

    private fun initAdapter(){
        adapter = IngredientsAdapter(ingredients, this)
        rv.adapter = adapter
        adapter?.itemCount?.minus(1)?.let { rv.scrollToPosition(it) }
    }

    private fun initRecycler(){
        rv = binding.rvIngredients
        rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun addIngredient(){
        textIngredient = binding.etIngredient.text.toString().trim()
        textNum = binding.etNum.text.toString()
        textMeasure = (binding.spinnerMeasure.selectedItem as Measure).name
        if (textIngredient.isNotEmpty() && textNum.isNotEmpty()){
            ingredients.add(Ingredients(textIngredient, textNum.toDouble(), Measure.valueOf(textMeasure)))
            binding.etIngredient.setText("")
            binding.etNum.setText("")
        }
        else{
            Toast.makeText(context, "Все поля должны быть заполнены!", Toast.LENGTH_LONG).show()
        }
        initAdapter()
    }

    private fun inputFilters(){
        binding.etNum.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        binding.etNum.filters = arrayOf(DecimalDigitsInputFilter(2))
    }

}