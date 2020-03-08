package br.com.motoclub_app.view.activity.motoclube

import br.com.motoclub_app.core.contract.BasePresenter
import br.com.motoclub_app.interactor.motoclube.MotoclubeInteractor
import br.com.motoclub_app.interactor.user.UserInteractor
import br.com.motoclub_app.model.Motoclube
import br.com.motoclub_app.repository.user.UserCacheRepository
import br.com.motoclub_app.view.activity.motoclube.contract.MotoclubeActivityPresenter
import br.com.motoclub_app.view.activity.motoclube.contract.MotoclubeActivityView
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class MotoclubeActivityPresenterImpl @Inject constructor(val view: MotoclubeActivityView) :
    BasePresenter(),
    MotoclubeActivityPresenter {

    @Inject
    lateinit var motoclubeInteractor: MotoclubeInteractor

    @Inject
    lateinit var userInteractor: UserInteractor

    private val onComplete: () -> Unit = view::onSave

    private val onSuccess: (mcRef: DocumentReference, mc: Motoclube) -> Unit = { mcRef, mc ->

        UserCacheRepository.currentUser?.let {
            val user = it
            user.motoclubeRef = mcRef
            user.motoclubeNome = mc.nome
            userInteractor.setCache(user)
            userInteractor.save(user).subscribe()
        }

        onComplete()
    }

    private val onError: (error: Throwable) -> Unit = { error -> view.showError(error.toString()) }

    override fun save(motoclube: Motoclube) {

        FirebaseFirestore.getInstance().runBatch {
            val disposable =
                if (motoclube.id == null) {
                    motoclubeInteractor.add(motoclube)
                        .subscribe({ ref -> onSuccess(ref, motoclube) }, onError)
                } else
                    motoclubeInteractor.save(motoclube).subscribe(onComplete, onError)

            compositeDisposable.add(disposable)
        }
    }

    override fun loadById(id: String) {

        val disposable =
            motoclubeInteractor.loadById(id).subscribe(view::onLoadMotoclube, onError)

        compositeDisposable.add(disposable)
    }

    override fun quit() {

        val disposable = motoclubeInteractor.quit().subscribe({
            userInteractor.setCache(UserCacheRepository.currentUser!!)
            view.onSave()
        }, onError)

        compositeDisposable.add(disposable)
    }

    override fun requestEntrance(mcId: String) {

        val disposable = motoclubeInteractor.requestEntrance(mcId)
            .subscribe({
                userInteractor.setCache(UserCacheRepository.currentUser!!)
                view.onSave()
            }, onError)

        compositeDisposable.add(disposable)
    }

    override fun getUserReference(userId: String): DocumentReference =
        userInteractor.getUserReference(userId)
}