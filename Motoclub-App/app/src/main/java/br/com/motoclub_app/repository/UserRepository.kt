package br.com.motoclub_app.repository

import android.content.SharedPreferences
import br.com.motoclub_app.core.Repository
import br.com.motoclub_app.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class UserRepository @Inject constructor() : Repository<Long, User>() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var gson: Gson

    override fun findById(id: Long): User? {
        val users = loadAll()
        return users?.filter { it.id == id }?.first()
    }

    override fun loadAll(): MutableList<User>? {
        val users = sharedPreferences.getString("users", "")

        if (users == null || users.isEmpty())
            return null

        val arrayUserType = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(users, arrayUserType)
    }

    override fun save(model: User) {

        var users: MutableList<User> = loadAll() ?: mutableListOf()

        if (model.id == null) {

            if (users.isEmpty()) {
                model.id = 1L
            } else {
                model.id = users[users.size - 1].id!! + 1
            }

            users.add(model)
        } else {

            val u = findById(model.id!!)

            users[users.indexOf(u)] = model
        }

        sharedPreferences.edit().putString("users", gson.toJson(users)).apply()
    }

    override fun delete(id: Long) {

        val users = loadAll()

        users?.forEach {
            if (it.id == id) {
                users.remove(it)
            }
        }

        sharedPreferences.edit().putString("users", gson.toJson(users)).apply()
    }

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

    companion object {
        var loggedUser: User? = null
    }
}
