package br.com.motoclub_app.view.fragment.motoclube.lista.contract

import br.com.motoclub_app.core.contract.View
import br.com.motoclub_app.view.fragment.Item

interface MotoclubesFragmentView : View {

    fun onLoadMotoclubes(motoclubes: MutableList<Item>)

    fun showError(msg: String?)

    fun onSolicitarEntrada()
}