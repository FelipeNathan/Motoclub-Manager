package br.com.motoclub_app.view.fragment.evento.lista

import br.com.motoclub_app.type.EventType
import br.com.motoclub_app.view.fragment.Item
import br.com.motoclub_app.view.fragment.evento.lista.contract.EventosPresenter
import br.com.motoclub_app.view.fragment.evento.lista.contract.EventosView
import javax.inject.Inject

class EventosPresenterImpl @Inject constructor(val view: EventosView) : EventosPresenter {

    override fun loadEvents(eventType: EventType) {

        val events = if (eventType == EventType.PUBLIC) {
            getPublicEvents()
        } else {
            getPrivateEventos()
        }

        view.onLoadEvents(events)
    }

    private fun getPrivateEventos(): MutableList<Item> {

        val items = mutableListOf<Item>()

        items.add(
            Item(
                mainInfo = "Festa junina",
                subInfo = "Sol da Liberdade: 14/04/2019",
                subInfo2 = "Contato: Juarez (47) 99999-9999"
            )
        )

        return items
    }

    private fun getPublicEvents(): MutableList<Item> {

        val items = mutableListOf<Item>()

        items.add(
            Item(
                mainInfo = "5o aniversário Sol da Liberdade",
                subInfo = "Sol da Liberdade: 14/04/2020",
                subInfo2 = "Contato: Juarez (47) 99999-9999"
            )
        )

        items.add(
            Item(
                mainInfo = "16o aniversário 100 Limite",
                subInfo = "100 Limite: 07/04/2020",
                subInfo2 = "Contato: Alguém lá (47) 99999-9999"
            )
        )

        items.add(
            Item(
                mainInfo = "12o aniversário Falcões Raça Liberta",
                subInfo = "Falcões Raça Liberta: 17/08/2019",
                subInfo2 = "Contato: Barata (47) 99999-9999"
            )
        )

        return items

    }
}