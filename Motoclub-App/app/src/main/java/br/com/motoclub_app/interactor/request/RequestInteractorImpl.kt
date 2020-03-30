package br.com.motoclub_app.interactor.request

import br.com.motoclub_app.model.Motoclube
import br.com.motoclub_app.model.Request
import br.com.motoclub_app.model.User
import br.com.motoclub_app.repository.motoclube.MotoclubeRepository
import br.com.motoclub_app.repository.request.RequestRepository
import br.com.motoclub_app.repository.user.UserRepository
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class RequestInteractorImpl @Inject constructor(
    private val requestRepository: RequestRepository,
    private val userRepository: UserRepository,
    private val motoclubeRepository: MotoclubeRepository
) : RequestInteractor {

    override fun waitingAcceptance(mcId: String?): Flowable<Request?> {

        return if (mcId != null) {
            requestRepository.findById(mcId)
                .map { it.toObject(Request::class.java) }
        } else {
            Flowable.empty()
        }
    }

    override fun userWaitingAcceptance(user: User?): Flowable<Request?> {

        return if (user != null) {
            waitingAcceptance(user.motoclubeRef?.id).filter { request ->
                request.users?.map { u -> u.id }?.contains(user.id) ?: false
            }
        } else {
            Flowable.empty();
        }

    }

    override fun findRequestByMotoclub(mcId: String): Flowable<Request?> {
        return requestRepository.findById(mcId).map { it.toObject(Request::class.java) }
    }

    override fun requestAcceptance(mcId: String, user: User): Disposable {

        return requestRepository.findById(mcId)
            .map { requestSnapshot ->

                var request = requestSnapshot.toObject(Request::class.java)

                if (request == null) {
                    val motoclubeRef = motoclubeRepository.getRefereceById(mcId)
                    request = Request(motoclubeRef, null)
                }

                request
            }
            .subscribe { request ->
                request!!.users?.add(userRepository.getRefereceById(user.id!!))
                requestRepository.save(request).subscribe()
            }
    }

    override fun answerRequest(motoclube: Motoclube, user: User, accept: Boolean): Disposable? {
        return requestRepository.findById(motoclube.id!!)
            .map { it.toObject(Request::class.java) }
            .subscribe { request ->

                request!!.users?.remove(userRepository.getRefereceById(user.id!!))
                requestRepository.save(request).subscribe()

                if (accept) {
                    val userRef = userRepository.getRefereceById(user.id!!)
                    motoclube.integrantesRef?.add(userRef)
                    motoclubeRepository.save(motoclube).subscribe()
                }
            }
    }
}