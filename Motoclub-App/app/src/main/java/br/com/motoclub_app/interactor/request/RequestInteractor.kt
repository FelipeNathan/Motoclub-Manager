package br.com.motoclub_app.interactor.request

import br.com.motoclub_app.model.Motoclube
import br.com.motoclub_app.model.Request
import br.com.motoclub_app.model.User
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable

interface RequestInteractor {

    fun waitingAcceptance(mcId: String?): Flowable<Request?>

    fun userWaitingAcceptance(user: User?): Flowable<Request?>

    fun findRequestByMotoclub(mcId: String): Flowable<Request?>

    fun requestAcceptance(mcId: String, user: User): Disposable

    fun answerRequest(motoclube: Motoclube, user: User, accept: Boolean): Disposable?
}