package br.com.motoclub_app.view.activity.startup.di

import android.app.Activity
import android.content.Intent
import android.util.Log
import br.com.motoclub_app.core.contract.BasePresenter
import br.com.motoclub_app.interactor.firebase.FirebaseInteractor
import br.com.motoclub_app.interactor.user.UserInteractor
import br.com.motoclub_app.view.activity.login.LoginActivity
import br.com.motoclub_app.view.activity.login.LoginPresenterImpl
import br.com.motoclub_app.view.activity.main.MainActivity
import br.com.motoclub_app.view.activity.startup.contract.StartupPresenter
import br.com.motoclub_app.view.activity.startup.contract.StartupView
import javax.inject.Inject

class StartupPresenterImpl @Inject constructor(
    private val view: StartupView,
    private val firebaseInteractor: FirebaseInteractor,
    private val userInteractor: UserInteractor
) : BasePresenter(), StartupPresenter {

    val context by lazy {
        view as Activity
    }

    override fun onCreate() {

        Log.i(LoginPresenterImpl.TAG, "Validando se existe usuário logado")

        if (firebaseInteractor.getCurrentUser() != null) {
            updateCacheAndLogin(firebaseInteractor.getCurrentUser()!!.uid)
        } else {
            navigateToLoginActivity()
        }
    }

    private fun updateCacheAndLogin(uuid: String) {

        val disposable = userInteractor.findById(uuid)
            .subscribe { user ->
                userInteractor.setCache(user!!)
                navigateToMainActivity()
            }

        compositeDisposable.add(disposable)
    }

    private fun navigateToMainActivity() {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

    companion object {
        val TAG = StartupPresenterImpl::class.java.simpleName
    }
}