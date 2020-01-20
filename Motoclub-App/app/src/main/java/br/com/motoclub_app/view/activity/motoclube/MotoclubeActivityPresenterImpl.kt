package br.com.motoclub_app.view.activity.motoclube

import br.com.motoclub_app.interactor.MotoclubeInteractor
import br.com.motoclub_app.model.Motoclube
import br.com.motoclub_app.repository.MotoclubeRepository
import br.com.motoclub_app.repository.UserRepository
import br.com.motoclub_app.view.activity.motoclube.contract.MotoclubeActivityPresenter
import br.com.motoclub_app.view.activity.motoclube.contract.MotoclubeActivityView
import javax.inject.Inject

class MotoclubeActivityPresenterImpl @Inject constructor(val view: MotoclubeActivityView) : MotoclubeActivityPresenter {

    @Inject
    lateinit var motoclubeRepository: MotoclubeRepository

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var interactor: MotoclubeInteractor

    override fun salvar(motoclube: Motoclube) {

        try {

            motoclubeRepository.save(motoclube)

            UserRepository.loggedUser!!.motoclubeId = motoclube.id
            userRepository.save(UserRepository.loggedUser!!)
            userRepository.setCache(UserRepository.loggedUser!!)

            view.onSalvar()

        } catch (ex: Exception) {

            view.showError(ex.message)
        }
    }

    override fun loadById(id: Long): Motoclube {

        val motoclube = motoclubeRepository.findById(id)

        motoclube?.let { return@loadById it }

        throw Exception("Motoclube não encontrado pelo id $id")
    }

    override fun sair() {

        try {

            UserRepository.loggedUser!!.motoclubeId = null
            userRepository.save(UserRepository.loggedUser!!)
            userRepository.setCache(UserRepository.loggedUser!!)

            view.onSalvar()

        } catch (ex: Exception) {
            view.showError(ex.message)
        }
    }

    override fun solicitarEntrada(mcId: Long) {

        try {
            interactor.requestEntrance(mcId)
            view.onSalvar()
        } catch (ex: Exception) {
            view.showError(ex.message)
        }
    }
}