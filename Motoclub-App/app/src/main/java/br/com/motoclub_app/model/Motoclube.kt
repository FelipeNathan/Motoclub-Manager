package br.com.motoclub_app.model

import br.com.motoclub_app.core.model.AbstractModel
import java.util.*

data class Motoclube(
    var nome: String? = null,
    var presidente: User? = null,
    var dataFundacao: Calendar? = null,
    var imageId: String? = null
) : AbstractModel()