package com.gcdominium

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Cliente::class), version = 1)
abstract class GCDDatabase: RoomDatabase() {
    abstract fun clienteDAO(): ClienteDAO
}