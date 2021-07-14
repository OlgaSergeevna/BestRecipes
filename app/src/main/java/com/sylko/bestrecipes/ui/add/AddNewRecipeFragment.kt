package com.sylko.bestrecipes.ui.add

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.sylko.bestrecipes.R
import com.sylko.bestrecipes.databinding.FragmentAddNewRecipeBinding
import com.sylko.bestrecipes.pojo.TypeOfMeal
import com.sylko.bestrecipes.generalviewmodels.ListIngredientsViewModel

class AddNewRecipeFragment : Fragment(R.layout.fragment_add_new_recipe) {

    private lateinit var viewModel: AddNewRecipeViewModel
    private lateinit var binding: FragmentAddNewRecipeBinding
    private lateinit var listViewModel: ListIngredientsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddNewRecipeBinding.bind(view)
        //здесь я привязала ViewModel к активити, чтобы она была доступна из различных фрагментов
        listViewModel = ViewModelProvider(requireActivity()).get(ListIngredientsViewModel::class.java)

        binding.spinnerType.adapter = ArrayAdapter(
            this.requireContext(), android.R.layout.simple_spinner_dropdown_item, TypeOfMeal.values()
        )

        binding.btnSave.setOnClickListener {

            if ((listViewModel.ingredients.value?.size == 0)||(listViewModel.ingredients.value == null)) {
                Toast.makeText(context, "Не заполнен список ингредиентов!", Toast.LENGTH_LONG).show()
            } else {
                viewModel.addData(
                        binding.spinnerType.selectedItem.toString(),
                        binding.etName.text.toString(),
                        listViewModel.ingredients.value!!
                        //listViewModel.instructions.value!!
                )
            }
            message()
            //если рецепт сохранен, то закрываем фрагмент
            //а нужно сделать так, чтоб этот рецепт отобразился в форме просмотра
            viewModel.error?.observe(viewLifecycleOwner, {
            if (it == false){
                activity?.onBackPressed()
            }
            })
        }

        binding.btnListIngredients.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_nav_new_recipe_to_listOfIngredientsFragment)
        }

        binding.btnRecipe.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_nav_new_recipe_to_cookingInstructionsFragment)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(AddNewRecipeViewModel::class.java)

    }

    private fun message(){

            viewModel.message?.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        // очищаю ВСЕ viewModel, привязанные к activity, при выходе из редактирования рецепта
        activity?.viewModelStore?.clear()
    }

}