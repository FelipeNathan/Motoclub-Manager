package br.com.motoclub_app.core.model

import java.util.*

abstract class AbstractModel (
    var id: Long? = null,
    var dataCriacao: Calendar = Calendar.getInstance(),
    var dataAlteracao: Calendar = Calendar.getInstance())