package br.com.motoclub_app.repository.motoclube

import br.com.motoclub_app.core.repository.BaseCacheRepository
import br.com.motoclub_app.model.Motoclube
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class MotoclubeCacheRepository @Inject constructor() : BaseCacheRepository<Motoclube>() {

    override fun getEntity() = "motoclubes"
    override fun getTypeToken(): Type = object : TypeToken<List<Motoclube>>() {}.type

}