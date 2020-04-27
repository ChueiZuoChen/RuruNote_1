package com.example.rurunote_1.db

class ItemRepository(private val dao: ItemDao) {

    val items = dao.getAll()
    suspend fun insert(item: Item): Long {
        return dao.insert(item)
    }

    suspend fun update(item: Item): Int {
        return dao.update(item)
    }

    suspend fun delete(item: Item): Int {
        return dao.update(item)
    }
}