package br.com.motoclub_app.repository.user

import br.com.motoclub_app.model.User
import br.com.motoclub_app.repository.BaseFirebaseRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor() : BaseFirebaseRepository<User>() {

    override fun collection() = FirebaseFirestore.getInstance().collection("users")

    fun findByEmailAndPassword(email: String, password: String): Flowable<QuerySnapshot> {

        return RxFirestore.observeQueryRef(
            collection()
                .whereEqualTo("email", email)
                .whereEqualTo("password", password)
        ).subscribeOn(Schedulers.io())
    }

    fun getUserReference(userId: String): DocumentReference = collection().document(userId)
}