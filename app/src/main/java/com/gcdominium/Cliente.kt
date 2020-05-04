package com.gcdominium

import java.io.Serializable

class Cliente : Serializable {

    var id:Long = 0
    var nome = ""
    var foto = ""

    override fun toString(): String {
        return "Cliente(nome='$nome')"
    }
}