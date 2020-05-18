package com.gcdominium

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "cliente")
class Cliente : Serializable {

    @PrimaryKey
    var id:Long = 0
    var nome = ""
    var cpf = ""
    var dataN = ""
    var foto = ""


    override fun toString(): String {
        return "Cliente(nome='$nome')"
    }

    fun toId(): String {
        return "$id"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }

}