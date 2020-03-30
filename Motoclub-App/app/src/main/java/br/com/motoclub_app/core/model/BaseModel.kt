package br.com.motoclub_app.core.model

import com.google.firebase.firestore.DocumentId

abstract class BaseModel(@DocumentId open var id: String? = null)