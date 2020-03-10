package br.com.motoclub_app.interactor.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Maybe

interface FirebaseInteractor {

    fun getCurrentUser(): FirebaseUser?

    fun signInWithEmailAndPassword(email: String, password: String): Maybe<AuthResult>

    fun createUserWithEmailAndPassword(email: String, password: String): Maybe<AuthResult>

    fun signOut()
}