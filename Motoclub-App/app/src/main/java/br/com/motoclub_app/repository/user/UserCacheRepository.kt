package br.com.motoclub_app.repository.user

import br.com.motoclub_app.core.repository.BaseCacheRepository
import br.com.motoclub_app.model.User
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class UserCacheRepository @Inject constructor() : BaseCacheRepository<User>() {

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
            editor.putString("currentUser", this)
            editor.apply()
        }

        currentUser = user
    }


    fun getCache(): User? {

        if (sharedPreferences.contains("currentUser")) {
            val userId: String? = sharedPreferences.getString("currentUser", null)

            userId?.apply {
                currentUser = findById(userId)
                return currentUser
            }
        }

        return null
    }

    fun removeCache() {
        if (sharedPreferences.contains("currentUser")) {
            sharedPreferences.edit().remove("currentUser").apply()
            currentUser = null
        }
    }

    override fun getEntity() = "users"

    override fun getTypeToken(): Type = object : TypeToken<List<User>>() {}.type

    companion object {
        var currentUser: User? = null
    }
}
