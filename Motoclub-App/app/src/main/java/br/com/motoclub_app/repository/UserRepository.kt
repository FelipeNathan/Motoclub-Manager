package br.com.motoclub_app.repository

import android.content.SharedPreferences
import br.com.motoclub_app.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class UserRepository @Inject constructor() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    fun loadById(id: Long): User {

        if (id == 1L){

        }

        return User(
            id = 1,
            apelido = "Capiroto",
            nome = "Felipe N. Campigoto",
            tipoSanguineo = "A+"
        )
    }

    private fun getUserList(): MutableList<User>? {
        val users = sharedPreferences.getString("users", "")

        if (users == null || users.isEmpty())
            return null

        val arrayUserType = object : TypeToken<List<User>>() {}.type
        return Gson().fromJson(users, arrayUserType)
    }

    fun getUserByEmail(email: String): User? {

        val userList = getUserList()

        userList?.forEach {
            if (it.email == email)
                return@getUserByEmail it
        }

        return null
    }

    fun getUserByEmailAndPassword(email: String, password: String): User? {

        val userList = getUserList()

        userList?.forEach {
            if (it.email == email && it.password == password)
                return@getUserByEmailAndPassword it
        }

        return null
    }

    fun setUser(user: User) {

        val editor = sharedPreferences.edit()

        editor.putString("loggedUser", Gson().toJson(user))
        editor.apply()

        loggedUser = user
    }

    fun addUser(user: User) {

        val gson = Gson()

        var userList = getUserList() ?: mutableListOf()
        userList.add(user)

        sharedPreferences.edit().putString("users", gson.toJson(userList)).apply()
    }

    fun getCache(): User? {

        if (sharedPreferences.contains("loggedUser")) {
            val user = sharedPreferences.getString("loggedUser", null)

            if (user != null && user.isNotEmpty()) {
                loggedUser = Gson().fromJson(user, User::class.java)
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
