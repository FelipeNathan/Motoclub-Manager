package br.com.motoclub_app.core.repository

import android.content.SharedPreferences
import br.com.motoclub_app.core.model.BaseModel
import com.google.gson.Gson
import javax.inject.Inject

abstract class BaseCacheRepository<Model : BaseModel> : Repository<Model> {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var gson: Gson

    override fun findById(id: String): Model? {
        val models = loadAll()
        return models?.first { it.id == id }

    }

    override fun loadAll(): MutableList<Model>? {
        val models = sharedPreferences.getString(getEntity(), "")

        if (models == null || models.isEmpty())
            return null

        return gson.fromJson(models, getTypeToken())
    }

    override fun save(model: Model) {

        val modelList: MutableList<Model> = loadAll() ?: mutableListOf()

        if (model.id == null) {

            if (modelList.isEmpty()) {
                model.id = ""
            } else {
                model.id = modelList[modelList.size - 1].id!! + 1
            }

            modelList.add(model)
        } else {

            val u = findById(model.id!!)

            modelList[modelList.indexOf(u)] = model
        }

        sharedPreferences.edit().putString(getEntity(), gson.toJson(modelList)).apply()
    }

    override fun delete(id: String) {

        val models = loadAll()

        models?.forEach {
            if (it.id == id) {
                models.remove(it)
            }
        }

        sharedPreferences.edit().putString(getEntity(), gson.toJson(models)).apply()
    }

    abstract fun getEntity(): String

}