package br.com.motoclub_app.model

import br.com.motoclub_app.core.model.BaseModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude

data class Request(
    private var motoclubeRef: DocumentReference? = null,
    var users: MutableList<DocumentReference>? = mutableListOf()
) : BaseModel() {

    init {
        id = motoclubeRef?.id
        users = users ?: mutableListOf()
    }
}