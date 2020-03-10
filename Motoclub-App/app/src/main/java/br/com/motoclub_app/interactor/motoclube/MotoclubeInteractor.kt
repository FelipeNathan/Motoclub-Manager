package br.com.motoclub_app.interactor.motoclube

import br.com.motoclub_app.model.Motoclube
import com.google.firebase.firestore.DocumentReference
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface MotoclubeInteractor {

    fun save(motoclube: Motoclube): Completable

    fun add(motoclube: Motoclube): Single<DocumentReference>

    fun loadById(id: String) : Flowable<Motoclube>

    fun quit() : Completable

    fun requestEntrance(mcId: String): Completable

    fun loadMotoclubes() : Flowable<List<Motoclube>>

}