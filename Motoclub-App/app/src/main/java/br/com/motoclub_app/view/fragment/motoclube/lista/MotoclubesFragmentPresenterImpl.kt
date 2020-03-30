package br.com.motoclub_app.view.fragment.motoclube.lista

import br.com.motoclub_app.core.contract.BasePresenter
import br.com.motoclub_app.interactor.motoclube.MotoclubeInteractor
import br.com.motoclub_app.view.fragment.Item
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesFragmentPresenter
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesFragmentView
import javax.inject.Inject

class MotoclubesFragmentPresenterImpl @Inject constructor(
    private val view: MotoclubesFragmentView,
    private val motoclubeInteractor: MotoclubeInteractor
) : BasePresenter(),
    MotoclubesFragmentPresenter {

    override fun loadMotoclubes(lastItem: Item?) {

        val items = mutableListOf<Item>()

        val disposable = motoclubeInteractor.loadPaginated("nome", lastItem?.mainInfo, 10)
            .subscribe({ motoclubes ->

                motoclubes.forEach { mc ->
                    items.add(
                        Item(
                            id = mc.id,
                            mainInfo = mc.nome,
                            subInfo = "Presidente: ${mc.presidenteNome}",
                            image = mc.imageId
                        )
                    )
                }

                view.onLoadMotoclubes(items)

            }) {
                view.showError(it?.toString())
            }

        compositeDisposable.add(disposable)
    }

    override fun requestEntrance(mcId: String) {

        val disposable = motoclubeInteractor.requestEntrance(mcId)
        compositeDisposable.add(disposable)

        view.onRequestEntrance()
    }
}