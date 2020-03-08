package br.com.motoclub_app.repository.motoclube

import br.com.motoclub_app.model.Motoclube
import br.com.motoclub_app.repository.BaseFirebaseRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class MotoclubeRepository @Inject constructor() : BaseFirebaseRepository<Motoclube>() {

    override fun collection(): CollectionReference =
        FirebaseFirestore.getInstance().collection("motoclubes")

}