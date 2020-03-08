package br.com.motoclub_app.view.fragment.motoclube.lista

import android.util.Log
import br.com.motoclub_app.core.contract.BasePresenter
import br.com.motoclub_app.interactor.motoclube.MotoclubeInteractor
import br.com.motoclub_app.view.fragment.Item
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesFragmentPresenter
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesFragmentView
import javax.inject.Inject

class MotoclubesFragmentPresenterImpl @Inject constructor(val view: MotoclubesFragmentView) :
    BasePresenter(),
    MotoclubesFragmentPresenter {

    @Inject
    lateinit var motoclubeInteractor: MotoclubeInteractor

    override fun loadMotoclubes() {

        Log.i("Instance", motoclubeInteractor.toString())

        val disposable = motoclubeInteractor.loadMotoclubes().subscribe({ motoclubes ->

            val items = mutableListOf<Item>()

            motoclubes.forEach { mc ->
                run {
                    items.add(
                        Item(
                            id = mc.id,
                            mainInfo = mc.nome,
                            subInfo = "Presidente: ${mc.presidenteNome}",
                            image = mc.imageId
                        )
                    )
                }
            }

            view.onLoadMotoclubes(items)

        }) {
            view.showError(it?.toString())
        }

        compositeDisposable.add(disposable)
    }

    override fun requestEntrance(mcId: String) {

        val disposable = motoclubeInteractor.requestEntrance(mcId)
            .subscribe({
                view.onRequestEntrance()
            }) {
                view.showError(it?.toString())
            }

        compositeDisposable.add(disposable)
    }
}