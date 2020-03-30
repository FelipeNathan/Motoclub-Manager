package br.com.motoclub_app.view.fragment.integrante.lista

import br.com.motoclub_app.core.contract.BasePresenter
import br.com.motoclub_app.interactor.user.UserInteractor
import br.com.motoclub_app.view.fragment.Item
import br.com.motoclub_app.view.fragment.integrante.lista.contract.IntegrantesPresenter
import br.com.motoclub_app.view.fragment.integrante.lista.contract.IntegrantesView
import javax.inject.Inject

class IntegrantesPresenterImpl @Inject constructor(
    private val view: IntegrantesView,
    private val userInteractor: UserInteractor
) : BasePresenter(),
    IntegrantesPresenter {

    override fun loadIntegrantes(lastItem: Item?) {

        val items = mutableListOf<Item>()

        val disposable = userInteractor.loadPaginated("nome", lastItem?.subInfo, 20)
            .subscribe({ users ->

                users.forEach {
                    items.add(
                        Item(
                            id = it.id,
                            mainInfo = it.apelido ?: it.nome,
                            subInfo = it.nome,
                            subInfo2 = "Tipo Sanguíneo: ${it.tipoSanguineo}",
                            image = it.imageId
                        )
                    )
                }
                view.onLoadIntegrantes(items)
            }) {
                view.showMessage(it.message)
            }


        compositeDisposable.add(disposable)
    }
}