package com.example.rurunote_1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.rurunote_1.R
import com.example.rurunote_1.databinding.ItemListBinding
import com.example.rurunote_1.db.Item

class RecyclerViewAdapter:RecyclerView.Adapter<FirstFragmentViewHolder>() {

    private val itemList = ArrayList<Item>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstFragmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:ItemListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_list,parent,false)
        return FirstFragmentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: FirstFragmentViewHolder, position: Int) {
        holder.binding(itemList[position])
    }
    fun setList(items:List<Item>){
        itemList.clear()
        itemList.addAll(items)
    }
}

class FirstFragmentViewHolder(val binding:ItemListBinding): RecyclerView.ViewHolder(binding.root){
    fun binding(item: Item){

        binding.addressTv.text = item.address
        binding.itemnameTv.text = item.itemName
        binding.amountTv.text = item.amount.toString()
        binding.priceTv.text = item.price.toString()
    }
}