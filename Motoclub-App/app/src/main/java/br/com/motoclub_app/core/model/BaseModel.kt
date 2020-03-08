package br.com.motoclub_app.core.model

import com.google.firebase.firestore.DocumentId

abstract class BaseModel(@DocumentId var id: String? = null)