package com.saied.users.ui.profile

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.saied.users.R
import com.saied.users.data.model.User
import com.saied.users.databinding.FragmentProfileBinding
import com.saied.users.ui.auth.singup.SignUpValidationException
import kotlinx.android.synthetic.main.fragment_profile.*
import org.apache.commons.validator.routines.EmailValidator
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val args: ProfileFragmentArgs by navArgs()

    private val viewModel: ProfileViewModel by viewModel()

    private val emailValidator = EmailValidator.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser(args.userId).observe(viewLifecycleOwner, Observer(::onUserValue))
    }

    /**
     * updates to user are delivered to this method
     */
    private fun onUserValue(user: User?) {
        if (user == null) // When user is deleted we simply navigate up
            findNavController().navigateUp()
        else { // bind non-null user value to UI
            renderEmailValidationError(null)
            _binding?.fnameET?.setText(user.fullName)
            _binding?.unameET?.setText(user.email)
            _binding?.fnameET?.isEnabled = args.editable
            _binding?.unameET?.isEnabled = args.editable
            _binding?.deleteButton?.visibility = if (args.editable) View.VISIBLE else View.GONE
            _binding?.updateButton?.visibility = if (args.editable) View.VISIBLE else View.GONE
            user.picturePath?.let {
                with(BitmapFactory.decodeFile(it)) {
                    _binding?.imageView?.setImageBitmap(this)
                }
            }

            _binding?.let { binding ->
                updateButton.setOnClickListener {
                    updateUser(
                        binding.unameET.text.toString(),
                        binding.fnameET.text.toString(),
                        user
                    )
                }
            }

            _binding?.deleteButton?.setOnClickListener {
                viewModel.deleteUser(user)
            }
        }
    }

    private fun updateUser(nEmail: String, nFname: String, user: User) {
        if (emailValidator.isValid(nEmail))
            viewModel.updateUser(
                user.copy(email = nEmail, fullName = nFname).also {
                    it.id = user.id
                }
            )
        else
            renderEmailValidationError(
                SignUpValidationException.EmailValidationException().message!!
            )
    }

    private fun renderEmailValidationError(msg: String?) {
        _binding?.unameInputLayout?.error =
            msg
    }
}