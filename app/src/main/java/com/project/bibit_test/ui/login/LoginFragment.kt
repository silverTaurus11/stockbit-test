package com.project.bibit_test.ui.login

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.project.bibit_test.R
import com.project.bibit_test.Utils
import com.project.bibit_test.data.source.local.sharedpref.LOGIN_VIA_NORMAL
import com.project.bibit_test.data.source.local.sharedpref.model.ProfileItem
import com.project.bibit_test.databinding.FragmentLoginBinding
import com.project.bibit_test.ui.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment: Fragment(R.layout.fragment_login) {
    private val binding by viewBinding(FragmentLoginBinding::bind)

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserAlreadyLogin()
        initButtonListener()
        initFormValidationObserver()
        initBackPressed()
    }

    private fun initUserAlreadyLogin(){
        loginViewModel.isUserAlreadyLogin.observe(viewLifecycleOwner, Observer {
            if(it){
                launchMainFragment()
            }
        })
    }

    private fun initButtonListener(){
        binding.loginButton.setOnClickListener {
            binding.usernameEditLayout.error = null
            binding.passwordEditLayout.error = null
            loginViewModel.doLogin(ProfileItem(
                    binding.usernameEditText.text.toString().trim(),
                    binding.passwordEditText.text.toString().trim(),
                    LOGIN_VIA_NORMAL))
            Utils.hideSoftKeyboard(requireActivity())
        }
    }

    private fun launchMainFragment(){
        Navigation.findNavController(requireView()).navigate(R.id.action_navigation_login_to_navigation_main)
    }

    private fun initFormValidationObserver(){
        loginViewModel.formValidation.observe(viewLifecycleOwner, Observer {
            when(it){
                LoginViewModel.Validation.USERNAME_INVALID ->
                    binding.usernameEditLayout.error = getString(R.string.username_is_required)
                LoginViewModel.Validation.PASSWORD_INVALID ->
                    binding.passwordEditLayout.error = getString(R.string.password_is_required)
                else -> return@Observer
            }
        })
    }

    private fun initBackPressed(){
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}