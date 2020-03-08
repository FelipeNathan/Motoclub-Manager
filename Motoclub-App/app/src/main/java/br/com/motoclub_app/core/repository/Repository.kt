package br.com.motoclub_app.core.repository

import br.com.motoclub_app.core.model.BaseModel
import java.lang.reflect.Type

interface Repository<Model : BaseModel> {

    fun findById(id: String): Model?

    fun loadAll(): MutableList<Model>?

    fun save(model: Model)

    fun delete(id: String)

    fun getTypeToken(): Type

}