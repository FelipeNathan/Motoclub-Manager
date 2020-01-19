package br.com.motoclub_app.view.fragment.motoclube.lista

import br.com.motoclub_app.repository.MotoclubeRepository
import br.com.motoclub_app.view.fragment.Item
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesFragmentPresenter
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesFragmentView
import javax.inject.Inject

class MotoclubesFragmentPresenterImpl @Inject constructor(val view: MotoclubesFragmentView) :
    MotoclubesFragmentPresenter {

    @Inject
    lateinit var motoclubeRepository: MotoclubeRepository

    override fun loadMotoclubes() {


        val items = mutableListOf<Item>()

        motoclubeRepository.loadAll()?.forEach {

            items.add(
                Item(
                    id = it.id,
                    mainInfo = it.nome,
                    subInfo = "Presidente: ${it.presidente?.nome}",
                    image = it.imageId
                )
            )

        }

        view.onLoadMotoclubes(items)
    }
}