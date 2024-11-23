package com.exal.grocerease.view.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.exal.grocerease.databinding.FragmentHomeBinding
import com.exal.grocerease.helper.rupiahFormatter
import com.exal.grocerease.viewmodel.HomeViewModel
import com.exal.grocerease.R
import com.exal.grocerease.view.adapter.RecentlyAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private var totalPrice = 0
    private var totalItem = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        // Tampilkan total price dan total items ke UI
        binding.total.text = rupiahFormatter(totalPrice)
        binding.items.text = "$totalItem Items"
    }

    private fun setupRecyclerView() {
        // Load data from string-array resources
        val itemPrices = resources.getStringArray(R.array.item_prices).map { it.toInt() }
        val itemTotals = resources.getStringArray(R.array.item_totals).map { it.toInt() }
        val itemDates = resources.getStringArray(R.array.item_dates)
        val itemImages = resources.obtainTypedArray(R.array.item_images)

        // Prepare the data for RecyclerView
        val itemList = mutableListOf<Item>()
        for (i in 0 until 5) { // Ambil 5 data pertama
            // Ambil data untuk item
            val price = itemPrices[i]
            val total = itemTotals[i]
            val imageRes = itemImages.getResourceId(i, -1)

            // Hitung total price dan total items
            totalPrice += price
            totalItem += total

            itemList.add(
                Item(
                    itemImage = imageRes,
                    itemPrice = price,
                    itemTotal = total,
                    itemDate = itemDates[i]
                )
            )
        }
        itemImages.recycle()

        // Set up RecyclerView adapter
        val adapter = RecentlyAdapter(itemList)
        binding.itemRv.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class Item(
    val itemImage: Int,
    val itemPrice: Int,
    val itemTotal: Int,
    val itemDate: String
)
