package br.com.motoclub_app.view.fragment.integrante.lista.contract

import br.com.motoclub_app.core.contract.View
import br.com.motoclub_app.view.fragment.Item

interface IntegrantesView : View {

    fun onLoadIntegrantes(integrantes: MutableList<Item>)
}