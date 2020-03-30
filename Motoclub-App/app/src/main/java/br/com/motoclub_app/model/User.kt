package br.com.motoclub_app.model

import br.com.motoclub_app.core.model.BaseModel
import br.com.motoclub_app.type.CargoType
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude

data class User(
    var nome: String? = null,
    var apelido: String? = null,
    var email: String? = null,
    @Exclude var password: String? = null,
    var tipoSanguineo: String? = null,
    var nascimento: Timestamp? = null,
    var cargo: CargoType? = null,
    var telefone: String? = null,
    var imageId: String? = null,
    var motoclubeNome: String? = null,
    var motoclubeRef: DocumentReference? = null
) : BaseModel()
