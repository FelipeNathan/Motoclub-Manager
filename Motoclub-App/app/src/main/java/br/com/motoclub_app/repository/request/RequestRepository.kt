package br.com.motoclub_app.repository.request

import br.com.motoclub_app.model.Request
import br.com.motoclub_app.repository.BaseFirebaseRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class RequestRepository @Inject constructor() : BaseFirebaseRepository<Request>() {

    override fun collection(): CollectionReference =
        FirebaseFirestore.getInstance().collection("request")
}