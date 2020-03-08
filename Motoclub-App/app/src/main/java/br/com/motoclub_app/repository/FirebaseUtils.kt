package br.com.motoclub_app.repository

import br.com.motoclub_app.core.model.AbstractModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

fun <T : AbstractModel> DocumentSnapshot.toModel(model: Class<T>): T? {

    val instance = this.toObject(model)
    instance?.id = this.reference.id
    return instance
}

fun <T : AbstractModel> QuerySnapshot.toModels(model: Class<T>): List<T> {

    val instances = mutableListOf<T>()

    this.forEach {

        val instance = it.toObject(model)
        instance.id = it.reference.id

        instances.add(instance)
    }

    return instances
}
