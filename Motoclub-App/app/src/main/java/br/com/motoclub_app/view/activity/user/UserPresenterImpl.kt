package br.com.motoclub_app.view.activity.user

import br.com.motoclub_app.view.activity.user.contract.UserPresenter
import br.com.motoclub_app.view.activity.user.contract.UserView
import br.com.motoclub_app.model.User
import br.com.motoclub_app.repository.UserRepository
import java.lang.Exception
import javax.inject.Inject

class UserPresenterImpl @Inject constructor(val view: UserView): UserPresenter {

    @Inject
    lateinit var userRepository: UserRepository

    override fun salvar(user: User) {

        try {

            if (user.id == null)
                validateOnSave(user)

            userRepository.addUser(user)
            userRepository.setUser(user)
            view.onSalvar()

        } catch (e: Exception) {
            view.showError(e.message)
        }
    }

    private fun validateOnSave(user: User) {

        if (userRepository.getUserByEmail(user.email!!) != null)
            throw Exception("Email já cadastrado")

    }

    override fun loadById(id: Long) = userRepository.loadById(id)
}