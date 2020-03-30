package br.com.motoclub_app.view.fragment.motoclube.lista.contract

import br.com.motoclub_app.core.contract.Presenter
import br.com.motoclub_app.view.fragment.Item

interface MotoclubesFragmentPresenter : Presenter {

    fun loadMotoclubes(lastItem: Item?)

    fun requestEntrance(mcId: String)
}