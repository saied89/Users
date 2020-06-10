package com.saied.users.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.saied.users.R
import com.saied.users.databinding.FragmentLoginBinding
import org.koin.android.ext.android.bind
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

        // set userLiveData listener
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer { user ->
            if (user == null)
                showToast("Wrong Username/Password")
            else
                showToast("User ${user.fullName}, welcome back!")
        })

        _binding?.apply { // if view is not destroyed
            // set login onclick listener
            loginButton.setOnClickListener {
                viewModel.getUser(unameET.text.toString(), passwordET.text.toString())
            }
        }
    }

    private fun showToast(message: String) {
        _binding?.root?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}