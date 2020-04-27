package com.example.rurunote_1.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item: Item):Long

    @Update
    suspend fun update(item: Item):Int

    @Delete
    suspend fun delete(item: Item):Int

    @Query("SELECT * FROM item_table")
    fun getAll():LiveData<List<Item>>
}