package br.com.motoclub_app.interactor.motoclube

import br.com.motoclub_app.interactor.request.RequestInteractor
import br.com.motoclub_app.model.Motoclube
import br.com.motoclub_app.repository.motoclube.MotoclubeCacheRepository
import br.com.motoclub_app.repository.motoclube.MotoclubeRepository
import br.com.motoclub_app.repository.user.UserCacheRepository
import br.com.motoclub_app.repository.user.UserRepository
import com.google.firebase.firestore.DocumentReference
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MotoclubeInteractorImpl @Inject constructor(
    private val repository: MotoclubeRepository,
    private val motoclubeCacheRepository: MotoclubeCacheRepository,
    private val userRepository: UserRepository,
    private val requestInteractor: RequestInteractor
) :
    MotoclubeInteractor {

    override fun save(motoclube: Motoclube): Completable = repository.save(motoclube)

    override fun add(motoclube: Motoclube): Single<DocumentReference> {
        return repository.add(motoclube)
    }

    override fun loadById(id: String): Flowable<Motoclube> {

        val motoclube = motoclubeCacheRepository.findById(id)

        if (motoclube != null)
            return Flowable.just(motoclube)

        return repository.findById(id).map { it.toObject(Motoclube::class.java) }
    }

    override fun quit(): Observable<Disposable> {

        val user = UserCacheRepository.currentUser!!
        val userRef = userRepository.getRefereceById(user.id!!)
        val motoclubeRef = user.motoclubeRef

        user.motoclubeRef = null
        val disposableUser = userRepository.save(user).subscribe()

        val disposableMotoclube = RxFirestore.observeDocumentRef(motoclubeRef!!)
            .map { it.toObject(Motoclube::class.java) }
            .subscribe {
                it?.integrantesRef?.remove(userRef)
                repository.save(it!!).subscribe()
            }

        return Observable.just(disposableUser, disposableMotoclube)
    }

    override fun requestEntrance(mcId: String): Disposable {
        return requestInteractor.requestAcceptance(mcId, UserCacheRepository.currentUser!!)
    }

    override fun loadPaginated(orderBy: String, last: String?, limit: Long) : Flowable<List<Motoclube>> =
        repository.paginate("nome", last, limit).map { it.toObjects(Motoclube::class.java) }

}