package com.example.architecturebase.network

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.databinding.FragmentRecyclerBinding
import androidx.lifecycle.Observer
import com.example.architecturebase.network.model.Post

class MVVMView : Fragment(){

    private val mvvmPresenter: MVVMPresenter = MVVMPresenter()

    init {
        lifecycle.addObserver(mvvmPresenter)
    }

    val mainAdapter = MainAdapter()

    var binding: FragmentRecyclerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.mainRV?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mainAdapter
        }


        val observer = Observer<List<Post>?> {
            mainAdapter.items = it
            binding?.listSRL?.isRefreshing = false

            if (it == null) {
                Toast.makeText(context, "Error. Data is empty.", Toast.LENGTH_SHORT).show()
            }
        }

        mvvmPresenter.data.observe(viewLifecycleOwner, observer)
    }
}