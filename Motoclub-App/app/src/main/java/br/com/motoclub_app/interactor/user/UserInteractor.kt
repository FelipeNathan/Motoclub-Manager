package br.com.motoclub_app.interactor.user

import br.com.motoclub_app.model.User
import com.google.firebase.firestore.DocumentReference
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UserInteractor {

    fun findByEmailAndPassword(email: String, password: String): Flowable<List<User>>

    fun getCurrentUser(): User?

    fun setCache(user: User)

    fun save(user: User): Completable

    fun findById(id: String): Flowable<User?>

    fun loadAll(): Flowable<List<User>>

    fun getUserReference(userId: String): DocumentReference

    fun loadPaginated(orderBy: String, last: String?, limit: Long): Flowable<List<User>>
}