package br.com.motoclub_app.view.activity.main

import android.util.Log
import br.com.motoclub_app.core.contract.BasePresenter
import br.com.motoclub_app.interactor.firebase.FirebaseInteractor
import br.com.motoclub_app.interactor.motoclube.MotoclubeInteractor
import br.com.motoclub_app.model.Motoclube
import br.com.motoclub_app.repository.user.UserCacheRepository
import br.com.motoclub_app.view.activity.main.contract.MainPresenter
import br.com.motoclub_app.view.activity.main.contract.MainView
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
    private val view: MainView,
    private val userRepository: UserCacheRepository,
    private val motoclubeInteractor: MotoclubeInteractor,
    private val firebaseInteractor: FirebaseInteractor
) : BasePresenter(), MainPresenter {

    override fun logout() {
        userRepository.removeCache()
        firebaseInteractor.signOut()
        view.onLogout()
    }

    override fun getMotoclube() {

        Log.i("Instance", motoclubeInteractor.toString())

        UserCacheRepository.currentUser!!.motoclubeRef?.let {
            it.get().addOnCompleteListener { mc ->
                view.onGetMotoclube(mc.result?.toObject(Motoclube::class.java)!!)
            }
        }
    }
}