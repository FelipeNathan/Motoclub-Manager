package br.com.motoclub_app.interactor.user

import br.com.motoclub_app.model.User
import br.com.motoclub_app.repository.user.UserCacheRepository
import br.com.motoclub_app.repository.user.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserInteractorImpl @Inject constructor() :
    UserInteractor {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var userCacheRepository: UserCacheRepository

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun findByEmailAndPassword(email: String, password: String): Flowable<List<User>> {
        return userRepository.findByEmailAndPassword(email, password)
            .map { it.toObjects(User::class.java) }
            .take(1)
    }

    override fun getCurrentUser(): User? = userCacheRepository.getCache()

    override fun setCache(user: User) = userCacheRepository.setCache(user)

    override fun save(user: User): Completable = userRepository.save(user)

    override fun findById(id: String): Flowable<User?> = userRepository.findById(id).map { it.toObject(User::class.java) }

    override fun loadAll(): Flowable<List<User>> =
        userRepository.loadAll().map { it.toObjects(User::class.java) }

    override fun getUserReference(userId: String): DocumentReference = userRepository.getUserReference(userId)
}