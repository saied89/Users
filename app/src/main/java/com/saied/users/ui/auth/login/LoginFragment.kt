package com.saied.users.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.saied.users.R
import com.saied.users.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModel()

    private var _binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply { // if view is not destroyed
            // set login onclick listener
            loginButton.setOnClickListener {
                lifecycleScope.launch {
                    val user =
                        viewModel.getUser(unameET.text.toString(), passwordET.text.toString())
                    if (user == null)
                        showToast("Wrong Username/Password")
                    else if (user.isAdmin)
                        findNavController().navigate(R.id.action_loginFragment_to_adminFragment)
                }

            }
            signUpButton.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_singUpFragment)
            }
        }
    }

    private fun showToast(message: String) {
        _binding?.root?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}