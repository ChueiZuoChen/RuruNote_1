package com.example.rurunote_1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rurunote_1.db.ItemRepository

class FirstFragmentViewModel(private val repository: ItemRepository) :ViewModel(){
    val items = repository.items

}