package com.gcdominium

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ClienteDAO {
    @Query("SELECT * FROM cliente where id = :id")
    fun getById(id: Long) : Cliente?

    @Query("SELECT * FROM cliente")
    fun findAll(): List<Cliente>

    @Insert
    fun insert(cliente: Cliente)

    @Delete
    fun delete(cliente: Cliente)

}