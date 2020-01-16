package br.com.motoclub_app.model

import java.util.*

data class Motoclube (
    var id: Long? = null,
    var nome: String? = null,
    var presidente: User? = null,
    var dataFundacao: Calendar? = null,
    var dataCriacao: Calendar? = Calendar.getInstance(),
    var dataAlteracao: Calendar? = Calendar.getInstance()
)