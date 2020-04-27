package com.example.rurunote_1.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "itemname")
    val itemName: String,
    @ColumnInfo(name = "anount")
    val amount: Int,
    @ColumnInfo(name = "price")
    val price: Double
)