package br.com.motoclub_app.interactor.motoclube

import br.com.motoclub_app.model.Motoclube
import br.com.motoclub_app.repository.motoclube.MotoclubeCacheRepository
import br.com.motoclub_app.repository.motoclube.MotoclubeRepository
import br.com.motoclub_app.repository.user.UserCacheRepository
import br.com.motoclub_app.repository.user.UserRepository
import com.google.firebase.firestore.DocumentReference
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class MotoclubeInteractorImpl @Inject constructor() :
    MotoclubeInteractor {

    @Inject
    lateinit var repository: MotoclubeRepository

    @Inject
    lateinit var motoclubeCacheRepository: MotoclubeCacheRepository

    @Inject
    lateinit var userCacheRepository: UserCacheRepository

    @Inject
    lateinit var userRepository: UserRepository

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

    override fun quit(): Completable {

        UserCacheRepository.currentUser!!.motoclubeRef = null
        return userRepository.save(UserCacheRepository.currentUser!!)
    }

    override fun requestEntrance(mcId: String): Completable {

//        UserCacheRepository.currentUser!!.motoclubeRef = mcId
        return userRepository.save(UserCacheRepository.currentUser!!)
    }

    override fun loadMotoclubes(): Flowable<List<Motoclube>> =
        repository.loadAll().map { it -> it.toObjects(Motoclube::class.java) }

}