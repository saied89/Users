package com.saied.users.ui.auth.singup

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.saied.users.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.aprilapps.easyphotopicker.*

private const val CHOOSER_PERMISSION_REQUEST_CODE = 7459

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null

    private val viewModel: SignupViewModel by viewModel()

    val easyImage by lazy {
        EasyImage.Builder(requireContext())
            .allowMultiple(false)
            .setCopyImagesToPublicGalleryFolder(true)
//            .setChooserTitle(getString(R.string.choose_profile_photo))
            .setChooserType(ChooserType.CAMERA_AND_GALLERY)
            .build()
    }

    // Permission for choosing image
    private val permissionsArray = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

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

    private fun runChooseImage() {
        if (checkPermissions())
            easyImage.openChooser(this)
        else
            requestPermissions(permissionsArray, CHOOSER_PERMISSION_REQUEST_CODE)
    }

    private fun checkPermissions(): Boolean {
        return permissionsArray.all {
            ContextCompat.checkSelfPermission(requireContext(), it) ==
                    PackageManager.PERMISSION_GRANTED
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        easyImage.handleActivityResult(
            requestCode,
            resultCode,
            data,
            requireActivity(),
            object : DefaultCallback() {
                override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                    viewModel.imagePathLiveData.value = imageFiles[0].file.absolutePath
                }
            })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CHOOSER_PERMISSION_REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED })
                easyImage.openChooser(this)
            else
                Snackbar.make(_binding?.root!!, "Permission not granted", Snackbar.LENGTH_SHORT)
                    .apply {
                        setAction("retry") {
                            runChooseImage()
                        }
                    }
                    .show()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.signupLiveData.observe(viewLifecycleOwner, Observer {
            _binding?.unameInputLayout?.error = null
            _binding?.passwordInputLayout?.error = null
            _binding?.cPasswordInputLayout?.error = null
            _binding?.fnameInputLayout?.error = null
            it.fold(onSuccess = {
                Toast.makeText(context, "User created!", Toast.LENGTH_SHORT).show()
                val res = findNavController().navigateUp()
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
                        cPassword = cPasswordET.text.toString(),
                        picturePath = viewModel.imagePathLiveData.value
                    )
                )
            }
        }

        _binding?.pickImageButton?.setOnClickListener {
            runChooseImage()
        }

        viewModel.imagePathLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                with(BitmapFactory.decodeFile(it)) {
                    _binding?.imageView?.setImageBitmap(this)
                }
            }
        })

    }

    private fun showSnackabr(message: String) {
        _binding?.root?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}

class SignUpData(
    val fname: String,
    val email: String,
    val password: String,
    val cPassword: String,
    val picturePath: String?
)