package br.com.motoclub_app.model

import br.com.motoclub_app.core.model.BaseModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude

data class Motoclube(
    var nome: String? = null,
    var presidenteNome: String? = null,
    var presidenteRef: DocumentReference? = null,
    var dataFundacao: Timestamp? = null,
    var imageId: String? = null,
    var integrantesRef: MutableList<DocumentReference>? = mutableListOf()
) : BaseModel() {

    init {
        integrantesRef = integrantesRef ?: mutableListOf()
    }
}