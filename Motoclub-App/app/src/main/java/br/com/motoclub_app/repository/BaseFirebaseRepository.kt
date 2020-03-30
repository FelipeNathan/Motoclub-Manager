package br.com.motoclub_app.repository

import br.com.motoclub_app.core.model.BaseModel
import com.google.firebase.firestore.*
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.Completable
import io.reactivex.Flowable

abstract class BaseFirebaseRepository<Model : BaseModel> {

    fun findById(id: String): Flowable<DocumentSnapshot> =
        RxFirestore.observeDocumentRef(collection().document(id))

    fun loadAll(): Flowable<QuerySnapshot> = RxFirestore.observeQueryRef(collection())

    fun save(model: Model): Completable {
        return RxFirestore.setDocument(collection().document(model.id!!), model)
    }

    fun delete(id: String): Completable = RxFirestore.deleteDocument(collection().document(id))

    fun getByDocument(document: DocumentReference) = RxFirestore.getDocument(document)

    fun add(model: Model) = RxFirestore.addDocument(collection(), model)

    fun getRefereceById(id: String): DocumentReference = collection().document(id)

    fun paginate(orderBy: String, last: String?, limit: Long): Flowable<QuerySnapshot> {

        var query = collection().limit(limit).orderBy(orderBy)

        if (last != null) {
            query = query.startAfter(last)
        }

        return RxFirestore.observeQueryRef(query)
    }

    abstract fun collection(): CollectionReference
}