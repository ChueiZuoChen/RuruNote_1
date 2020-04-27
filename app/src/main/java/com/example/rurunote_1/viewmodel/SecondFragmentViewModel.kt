package com.example.rurunote_1.viewmodel

import android.app.Application
import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.rurunote_1.MainActivity
import com.example.rurunote_1.db.Item
import com.example.rurunote_1.db.ItemRepository
import kotlinx.coroutines.launch


class SecondFragmentViewModel(private val repository: ItemRepository) : ViewModel(), Observable {
    private val TAGs: String = MainActivity::class.java.simpleName
    val items = repository.items

    @Bindable
    val inputAddress = MutableLiveData<String>()

    @Bindable
    val inputItemName = MutableLiveData<String>()

    @Bindable
    var inputAmount = MutableLiveData<String>()

    @Bindable
    var inputPrice = MutableLiveData<String>()

    val message: LiveData<Event<String>>
        get() = statusMessage
    private val statusMessage = MutableLiveData<Event<String>>()

    fun saveOnclick() {
        when {
            inputAddress.value.isNullOrEmpty() -> {
                statusMessage.value = Event("請輸入地址")
            }
            inputItemName.value.isNullOrBlank() -> {
                statusMessage.value = Event("請輸入品項")
            }
            inputAmount.value.isNullOrEmpty() -> {
                statusMessage.value = Event("請輸入數量")
            }
            inputPrice.value.isNullOrEmpty() -> {
                statusMessage.value = Event("請輸入價錢")
            }
            else -> {

                val address = inputAddress.value!!
                val itemName = inputItemName.value!!
                val amountString = inputAmount.value!!
                val priceString = inputPrice.value!!
                val afterCastAmount = amountString.toInt()
                val afterCastPrice = priceString.toDouble()
                insert(Item(0, address, itemName, afterCastAmount, afterCastPrice))
                Log.d(TAGs, "aaa")
            }
        }
    }


    private fun insert(item: Item) = viewModelScope.launch {
        val newRowId = repository.insert(item)
        if (newRowId > -1) {
            statusMessage.value = Event("新增成功")
        } else {
            statusMessage.value = Event("新增錯誤")
        }
    }

    fun update(item: Item) = viewModelScope.launch {
        val noOfRows = repository.update(item)
        if (noOfRows > 0) {
            inputAddress.value = null
            inputItemName.value = null
            inputAmount.value = null
            inputPrice.value = null
            statusMessage.value = Event("更新成功")
        } else {
            statusMessage.value = Event("更新錯誤")
        }
    }

    fun delete(item: Item) = viewModelScope.launch {
        val noOfRowsDelete = repository.delete(item)
        if (noOfRowsDelete > 0) {
            inputAddress.value = null
            inputItemName.value = null
            inputAmount.value = null
            inputPrice.value = null
            statusMessage.value = Event("刪除成功")
        } else {
            statusMessage.value = Event("刪除失敗")
        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}