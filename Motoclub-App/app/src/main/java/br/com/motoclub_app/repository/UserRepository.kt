package br.com.motoclub_app.repository

import br.com.motoclub_app.core.repository.AbstractRepository
import br.com.motoclub_app.model.User
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class UserRepository @Inject constructor() : AbstractRepository<User>() {

    fun getUserByEmail(email: String): User? {

        val userList = loadAll()

        userList?.forEach {
            if (it.email == email)
                return@getUserByEmail it
        }

        return null
    }

    fun getUserByEmailAndPassword(email: String, password: String): User? {

        val userList = loadAll()

        userList?.forEach {
            if (it.email == email && it.password == password)
                return@getUserByEmailAndPassword it
        }

        return null
    }

    fun setCache(user: User) {

        val editor = sharedPreferences.edit()

        user.id?.apply {
            editor.putLong("loggedUser", this)
            editor.apply()
        }

        loggedUser = user
    }


    fun getCache(): User? {

        if (sharedPreferences.contains("loggedUser")) {
            val userId: Long = sharedPreferences.getLong("loggedUser", 0)

            if (userId != 0L) {
                loggedUser = findById(userId)
                return loggedUser
            }
        }

        return null
    }

    fun removeCache() {
        if (sharedPreferences.contains("loggedUser")) {
            sharedPreferences.edit().remove("loggedUser").apply()
            loggedUser = null
        }
    }

    override fun getEntity() = "users"

    override fun getTypeToken(): Type = object : TypeToken<List<User>>() {}.type

    companion object {
        var loggedUser: User? = null
    }
}
