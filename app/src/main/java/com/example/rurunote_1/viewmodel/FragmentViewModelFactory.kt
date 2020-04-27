package com.example.rurunote_1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rurunote_1.db.ItemRepository
import java.lang.IllegalArgumentException

class FragmentViewModelFactory(private val repository: ItemRepository) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondFragmentViewModel::class.java)){
            return SecondFragmentViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(FirstFragmentViewModel::class.java)){
            return FirstFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow viewmodel class")
    }
}