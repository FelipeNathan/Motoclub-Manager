package br.com.motoclub_app.core

abstract class Repository<T, Model> {

    abstract fun findById(id: T): Model?

    abstract fun loadAll(): MutableList<Model>?

    abstract fun save(model: Model)

    abstract fun delete(id: T)

}