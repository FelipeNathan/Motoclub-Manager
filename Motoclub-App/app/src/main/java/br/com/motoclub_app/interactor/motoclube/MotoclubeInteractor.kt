package br.com.motoclub_app.interactor.motoclube

import br.com.motoclub_app.model.Motoclube
import com.google.firebase.firestore.DocumentReference
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

interface MotoclubeInteractor {

    fun save(motoclube: Motoclube): Completable

    fun add(motoclube: Motoclube): Single<DocumentReference>

    fun loadById(id: String) : Flowable<Motoclube>

    fun quit() : Observable<Disposable>

    fun requestEntrance(mcId: String): Disposable

    fun loadPaginated(orderBy: String, last: String?, limit: Long) : Flowable<List<Motoclube>>

}