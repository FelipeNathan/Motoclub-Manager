package br.com.motoclub_app.repository

import br.com.motoclub_app.core.model.BaseModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

abstract class BaseFirebaseRepository<Model : BaseModel> {

    fun findById(id: String): Flowable<DocumentSnapshot> = RxFirestore.observeDocumentRef(collection().document(id))

    fun loadAll(): Flowable<QuerySnapshot> = RxFirestore.observeQueryRef(collection())

    fun save(model: Model): Completable = RxFirestore.setDocument(collection().document(model.id!!), model)

    fun delete(id: String): Completable = RxFirestore.deleteDocument(collection().document(id))

    fun getByDocument(document: DocumentReference) = RxFirestore.getDocument(document)

    fun add(model: Model) = RxFirestore.addDocument(collection(), model)

    abstract fun collection() : CollectionReference
}