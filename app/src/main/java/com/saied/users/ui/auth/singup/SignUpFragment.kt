package com.saied.users.ui.auth.singup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.saied.users.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null

    private val viewModel: SignupViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.signupLiveData.observe(viewLifecycleOwner, Observer {
            _binding?.unameInputLayout?.error = null
            _binding?.passwordInputLayout?.error = null
            _binding?.cPasswordInputLayout?.error = null
            _binding?.fnameInputLayout?.error = null
            it.fold(onSuccess = {
                showSnackabr("Success!")
            }, onFailure = { exception ->
                when (exception) {
                    is SignUpValidationException.EmailValidationException ->
                        _binding?.unameInputLayout?.error = exception.message
                    is SignUpValidationException.PasswordValidationException ->
                        _binding?.passwordInputLayout?.error = exception.message
                    is SignUpValidationException.PasswordConfirmException ->
                        _binding?.cPasswordInputLayout?.error = exception.message
                    is SignUpValidationException.FullNameEmptyException ->
                        _binding?.fnameInputLayout?.error = exception.message
                }
            })
        })

        _binding?.apply {
            signUpButton.setOnClickListener {
                viewModel.submitSignUpData(
                    SignUpData(
                        fname = fnameET.text.toString(),
                        email = unameET.text.toString(),
                        password = passwordET.text.toString(),
                        cPassword = cPasswordET.text.toString()
                    )
                )
            }
        }


    }

    private fun showSnackabr(message: String) {
        _binding?.root?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}

class SignUpData(val fname: String, val email: String, val password: String, val cPassword: String)