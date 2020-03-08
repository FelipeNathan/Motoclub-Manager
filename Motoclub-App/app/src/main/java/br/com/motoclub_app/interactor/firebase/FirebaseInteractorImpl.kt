package br.com.motoclub_app.interactor.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import durdinapps.rxfirebase2.RxFirebaseAuth
import javax.inject.Inject

class FirebaseInteractorImpl @Inject constructor(): FirebaseInteractor {

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun getCurrentUser(): FirebaseUser? = auth.currentUser

    override fun signInWithEmailAndPassword(email: String, password: String) =
        RxFirebaseAuth.signInWithEmailAndPassword(auth, email, password)

    override fun createUserWithEmailAndPassword(email: String, password: String) =
        RxFirebaseAuth.createUserWithEmailAndPassword(auth, email, password)

    override fun signOut() = auth.signOut()
}