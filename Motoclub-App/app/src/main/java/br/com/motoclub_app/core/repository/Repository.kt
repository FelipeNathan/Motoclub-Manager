package br.com.motoclub_app.core.repository

import br.com.motoclub_app.core.model.AbstractModel
import java.lang.reflect.Type

interface Repository<Model : AbstractModel> {

    fun findById(id: Long): Model?

    fun loadAll(): MutableList<Model>?

    fun save(model: Model)

    fun delete(id: Long)

    fun getEntity(): String

    fun getTypeToken(): Type

}