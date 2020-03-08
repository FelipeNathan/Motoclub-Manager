package br.com.motoclub_app.view.activity.user

import android.util.Log
import br.com.motoclub_app.core.contract.BasePresenter
import br.com.motoclub_app.interactor.firebase.FirebaseInteractor
import br.com.motoclub_app.interactor.user.UserInteractor
import br.com.motoclub_app.model.User
import br.com.motoclub_app.repository.user.UserCacheRepository
import br.com.motoclub_app.view.activity.user.contract.UserPresenter
import br.com.motoclub_app.view.activity.user.contract.UserView
import javax.inject.Inject

class UserPresenterImpl @Inject constructor(val view: UserView) : BasePresenter(),
    UserPresenter {

    @Inject
    lateinit var userInteractor: UserInteractor

    @Inject
    lateinit var firebaseInteractor: FirebaseInteractor

    override fun save(user: User) {

        try {

            if (user.id == null) {
                createNewUser(user)
            } else {
                pushToDocument(user)
            }

        } catch (e: Exception) {
            view.showMessage(e.message)
        }
    }

    private fun createNewUser(user: User) {

        val disposable =
            firebaseInteractor.createUserWithEmailAndPassword(user.email!!, user.password!!)
                .subscribe({

                    user.id = it.user!!.uid
                    pushToDocument(user)

                }) {

                    Log.e(TAG, it.toString(), it)
                    view.showMessage(it.message)
                }

        compositeDisposable.add(disposable)
    }

    private fun pushToDocument(user: User) {

        val disposable = userInteractor.save(user)
            .subscribe({
                updateCacheAndUpdateView(user)
            }) {
                view.showMessage("Falha ao salvar dados")
            }

        compositeDisposable.add(disposable)
    }

    private fun updateCacheAndUpdateView(user: User) {

        if (UserCacheRepository.currentUser == null)
            userInteractor.setCache(user)

        view.onSave()
    }

    override fun loadById(id: String) {

        if (UserCacheRepository.currentUser!!.id == id) {

            Log.i(TAG, "The ID is of the Logged User")
            view.onLoadUser(UserCacheRepository.currentUser)

        } else {

            Log.i(TAG, "Loading user from repository")

            val disposable = userInteractor.findById(id)
                .subscribe({

                    if (it == null) {
                        view.showMessage("Usuário não encontrado")
                        return@subscribe
                    }

                    view.onLoadUser(it)

                }) {
                    view.showMessage("Usuário não encontrado")
                }

            compositeDisposable.add(disposable)
        }
    }

    companion object {
        var TAG = UserPresenterImpl::class.java.simpleName
    }
}