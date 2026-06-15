package com.example.brickmilio1.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brickmilio1.R
import com.example.brickmilio1.adapter.LegoSetAdapter
import com.example.brickmilio1.ui.AddSetActivity
import com.example.brickmilio1.viewmodel.LegoSetViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PorArmarFragment : Fragment() {

    private lateinit var viewModel: LegoSetViewModel
    private lateinit var adapter: LegoSetAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[LegoSetViewModel::class.java]
        adapter = LegoSetAdapter(viewModel)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.obtenerPorEstado("por_armar").observe(viewLifecycleOwner) { lista ->
            adapter.submitList(lista)
        }

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(requireContext(), AddSetActivity::class.java)
            intent.putExtra("estado", "por_armar")
            startActivity(intent)
        }
    }
}