package com.example.mapsdk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mapsdk.databinding.OrderItemBinding

class OrdersAdapter(data: List<Order>) : RecyclerView.Adapter<OrdersAdapter.OrderHolder>() {
    var data: List<Order> = data
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class OrderHolder(var binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val binding = OrderItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        with(holder) {
            binding.orderAddress.text = data[position].address
            binding.orderContent.text = data[position].content
            binding.orderName.text = data[position].name
            binding.addressLayout.setOnClickListener {
                binding.root.findNavController()
                    .navigate(R.id.action_ordersFragment_to_mapFragment,
                    bundleOf("order_name" to data[position].name))


            }
        }
    }

    override fun getItemCount() = data.size
}