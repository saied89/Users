package com.saied.users.ui.profile

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.saied.users.R
import com.saied.users.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val args: ProfileFragmentArgs by navArgs()

    private val viewModel: ProfileViewModel by viewModel()

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
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            _binding?.fnameET?.setText(it.fullName)
            _binding?.unameET?.setText(it.email)
            _binding?.fnameET?.isEnabled = args.editable
            _binding?.unameET?.isEnabled = args.editable
            _binding?.deleteButton?.visibility = if(args.editable) View.VISIBLE else View.GONE
            _binding?.updateButton?.visibility = if(args.editable) View.VISIBLE else View.GONE
            it.picturePath?.let {
                with(BitmapFactory.decodeFile(it)) {
                    _binding?.imageView?.setImageBitmap(this)
                }
            }
        })

        viewModel.getUser(args.userId)
    }
}