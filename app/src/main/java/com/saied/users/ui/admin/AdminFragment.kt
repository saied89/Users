package com.saied.users.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.saied.users.databinding.FragmentAdminBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminFragment : Fragment() {

    private var _binding: FragmentAdminBinding? = null

    private val viewModel: AdminViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userListLiveData.observe(viewLifecycleOwner, Observer {
            _binding?.tv?.text = it.map { it.fullName }.joinToString()
        })
    }

}