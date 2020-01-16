package br.com.motoclub_app.view.fragment.evento.lista.contract

import br.com.motoclub_app.core.contract.Presenter
import br.com.motoclub_app.type.EventType

interface EventosPresenter : Presenter {

    fun loadEvents(eventType: EventType)

}