package br.com.motoclub_app.view.fragment.integrante.lista

import br.com.motoclub_app.repository.UserRepository
import br.com.motoclub_app.view.fragment.Item
import br.com.motoclub_app.view.fragment.integrante.lista.contract.IntegrantesPresenter
import br.com.motoclub_app.view.fragment.integrante.lista.contract.IntegrantesView
import javax.inject.Inject

class IntegrantesPresenterImpl @Inject constructor(val view: IntegrantesView) : IntegrantesPresenter {

    @Inject
    lateinit var userRepository: UserRepository

    override fun loadIntegrantes() {

        val items = mutableListOf<Item>()
        
        userRepository.loadAll()?.forEach {

            items.add(
                Item(
                    id = it.id,
                    mainInfo = it.apelido ?: it.nome,
                    subInfo = it.nome,
                    subInfo2 = "Tipo Sanguíneo: ${it.tipoSanguineo}",
                    image = it.imageId
                )
            )

            view.onLoadIntegrantes(items)
        }

    }
}