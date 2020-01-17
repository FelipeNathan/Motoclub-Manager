package br.com.motoclub_app.view.activity.login

import android.util.Log
import br.com.motoclub_app.view.activity.login.contract.LoginPresenter
import br.com.motoclub_app.view.activity.login.contract.LoginView
import br.com.motoclub_app.repository.UserRepository
import javax.inject.Inject

class LoginPresenterImpl @Inject constructor(private val view: LoginView) : LoginPresenter {

    companion object {
        val TAG = LoginPresenterImpl::class.java.simpleName
    }

    @Inject
    lateinit var repository: UserRepository

    override fun onLogin(email: String, password: String) {

        val user = repository.getUserByEmailAndPassword(email, password)

        if (user == null) {
            view.showMessage("Usuário não encontrado")
        } else {
            repository.setCache(user)
            view.onLogin()
        }
    }

    override fun onCreate() {

        Log.i(TAG, "Validando se existe usuário logado")

        val user = repository.getCache()
        user?.apply { view.onLogin() }
    }
}