package br.com.motoclub_app.repository

import br.com.motoclub_app.core.repository.AbstractRepository
import br.com.motoclub_app.model.Motoclube
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class MotoclubeRepository @Inject constructor() : AbstractRepository<Motoclube>() {

    override fun getEntity() = "motoclubes"
    override fun getTypeToken(): Type = object : TypeToken<List<Motoclube>>() {}.type

}