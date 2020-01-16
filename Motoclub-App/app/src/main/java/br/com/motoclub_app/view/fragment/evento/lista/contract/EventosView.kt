package br.com.motoclub_app.view.fragment.evento.lista.contract

import br.com.motoclub_app.core.contract.View
import br.com.motoclub_app.view.fragment.Item

interface EventosView : View {

    fun onLoadEvents(events: MutableList<Item>)
}