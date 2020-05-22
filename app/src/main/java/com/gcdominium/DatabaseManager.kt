package com.gcdominium

import androidx.room.Room

object DatabaseManager {

    private var dbInstance: GCDDatabase
    init {
        val appContext = GCDApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            appContext,
            GCDDatabase::class.java,
            "gcd.sqlite"
        ).build()
    }

    fun getclienteDAO(): ClienteDAO {
        return dbInstance.clienteDAO()
    }
}