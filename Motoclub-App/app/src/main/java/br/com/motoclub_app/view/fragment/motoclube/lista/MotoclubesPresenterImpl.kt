package br.com.motoclub_app.view.fragment.motoclube.lista

import br.com.motoclub_app.view.fragment.Item
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesPresenter
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesView
import javax.inject.Inject

class MotoclubesPresenterImpl @Inject constructor(val view: MotoclubesView) : MotoclubesPresenter {

    override fun loadMotoclubes(){
        val items = mutableListOf<Item>()

        items.add(
            Item(
                id = 1,
                mainInfo = "Sol da Liberdade",
                subInfo = "Presidente: Juarez",
                subInfo2 = "20 Integrantes"
            )
        )

        items.add(
            Item(
                id = 2,
                mainInfo = "Falcões Raça Liberta",
                subInfo = "Barata",
                subInfo2 = "200 Integrantes"
            )
        )

        items.add(
            Item(
                id = 3,
                mainInfo = "100 Limite",
                subInfo = "Alguém lá",
                subInfo2 = "500 Integrantes"
            )
        )

        view.onLoadMotoclubes(items)
    }
}