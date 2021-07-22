package com.example.mapsdk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapsdk.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {
    lateinit var binding: FragmentOrdersBinding
    lateinit var adapter: OrdersAdapter
    private val orders: List<Order> by lazy {
        OrdersReader(requireContext()).read()!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = OrdersAdapter(orders)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(context)
    }
}