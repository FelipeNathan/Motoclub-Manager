package br.com.motoclub_app.view.activity.motoclube.contract

import br.com.motoclub_app.core.contract.Presenter
import br.com.motoclub_app.model.Motoclube
import com.google.firebase.firestore.DocumentReference

interface MotoclubeActivityPresenter : Presenter {

    fun save(motoclube: Motoclube)

    fun loadById(id: String)

    fun quit()

    fun requestEntrance(mcId: String)

    fun getUserReference(userId: String): DocumentReference
}