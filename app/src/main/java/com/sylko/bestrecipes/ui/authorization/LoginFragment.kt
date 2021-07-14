package com.sylko.bestrecipes.ui.authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sylko.bestrecipes.R
import com.sylko.bestrecipes.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

    }
}