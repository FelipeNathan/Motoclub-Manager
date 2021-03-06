package br.com.motoclub_app.view.activity.login

import android.util.Log
import br.com.motoclub_app.core.contract.BasePresenter
import br.com.motoclub_app.interactor.firebase.FirebaseInteractor
import br.com.motoclub_app.interactor.user.UserInteractor
import br.com.motoclub_app.view.activity.login.contract.LoginPresenter
import br.com.motoclub_app.view.activity.login.contract.LoginView
import javax.inject.Inject

class LoginPresenterImpl @Inject constructor(
    private val view: LoginView,
    private val userInteractor: UserInteractor,
    private val firebaseInteractor: FirebaseInteractor
) : BasePresenter(),
    LoginPresenter {

    override fun login(email: String, password: String) {

        val disposable = firebaseInteractor.signInWithEmailAndPassword(email, password)
            .subscribe({ _ ->

                Log.d(TAG, "Logado")
                updateCacheAndLogin(firebaseInteractor.getCurrentUser()!!.uid)

            }) {
                Log.w(TAG, "signInWithEmail:failure", it)
                view.showMessage("Falha na autenticação")
            }

        compositeDisposable.add(disposable)
    }

    private fun updateCacheAndLogin(uuid: String) {

        val disposable = userInteractor.findById(uuid)

            .subscribe { user ->

                userInteractor.setCache(user!!)
                view.onLogin()
            }

        compositeDisposable.add(disposable)
    }

    companion object {
        val TAG = LoginPresenterImpl::class.java.simpleName
    }
}