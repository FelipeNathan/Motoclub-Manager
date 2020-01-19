package br.com.motoclub_app.model

import br.com.motoclub_app.core.model.AbstractModel
import br.com.motoclub_app.type.CargoType
import java.util.*

data class User(
    var nome: String? = null,
    var apelido: String? = null,
    var email: String? = null,
    var password: String? = null,
    var tipoSanguineo: String? = null,
    var nascimento: Calendar? = null,
    var cargo: CargoType? = null,
    var telefone: String? = null,
    var imageId: String? = null,
    var motoclubeId: Long? = null
) : AbstractModel()
