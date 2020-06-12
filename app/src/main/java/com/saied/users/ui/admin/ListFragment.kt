package com.saied.users.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.saied.users.databinding.FragmentListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val viewModel: ListViewModel by viewModel()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        UserAdapter(
            onDeleteClick = viewModel::deleteUser,
            onItemClick = {
                val action = ListFragmentDirections.actionAdminFragmentToProfileFragment(
                    false,
                    it.id
                )
                findNavController().navigate(action)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.recyclerView?.run { // Setup recyclerview
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = this@ListFragment.adapter
        }

        // Subscribe to livedata
        viewModel.userListLiveData.observe(viewLifecycleOwner, Observer(adapter::submitList))
    }

}