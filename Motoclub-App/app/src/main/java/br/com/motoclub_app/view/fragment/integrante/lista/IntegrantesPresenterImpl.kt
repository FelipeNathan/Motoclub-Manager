package br.com.motoclub_app.view.fragment.integrante.lista

import br.com.motoclub_app.repository.UserRepository
import br.com.motoclub_app.view.fragment.Item
import br.com.motoclub_app.view.fragment.integrante.lista.contract.IntegrantesPresenter
import br.com.motoclub_app.view.fragment.integrante.lista.contract.IntegrantesView
import javax.inject.Inject

class IntegrantesPresenterImpl @Inject constructor(val view: IntegrantesView) : IntegrantesPresenter {

    @Inject
    lateinit var userRepository: UserRepository

    override fun loadIntegrantes() {

        val items = mutableListOf<Item>()


//        items.add(
//            Item(
//                id = 1,
//                mainInfo = "Capiroto",
//                subInfo = "Felipe Nathan Campigoto",
//                subInfo2 = "Tipo Sanguíneo: A+",
//                image = "https://avatars3.githubusercontent.com/u/16759812?s=460&v=4"
//            )
//        )
//
//        items.add(
//            Item(
//                id = 2,
//                mainInfo = "Juarez",
//                subInfo = "Juarez Costa",
//                subInfo2 = "Tipo Sanguíneo: O+"
//            )
//        )
//
//        items.add(
//            Item(
//                id = 3,
//                mainInfo = "Isah",
//                subInfo = "Isabela Fernandes",
//                subInfo2 = "Tipo Sanguíneo: B+"
//            )
//        )
//
//        items.add(
//            Item(
//                id = 4,
//                mainInfo = "Adri",
//                subInfo = "Adriana Costa",
//                subInfo2 = "Tipo Sanguíneo: O+"
//            )
//        )
//
//        items.add(
//            Item(
//                id = 5,
//                mainInfo = "Capiroto",
//                subInfo = "Felipe Nathan Campigoto",
//                subInfo2 = "Tipo Sanguíneo: A+"
//            )
//        )
//
//        items.add(
//            Item(
//                id = 6,
//                mainInfo = "Juarez",
//                subInfo = "Juarez Costa",
//                subInfo2 = "Tipo Sanguíneo: O+"
//            )
//        )
//
//        items.add(
//            Item(
//                id = 7,
//                mainInfo = "Isah",
//                subInfo = "Isabela Fernandes",
//                subInfo2 = "Tipo Sanguíneo: B+"
//            )
//        )
//
//        items.add(
//            Item(
//                id = 8,
//                mainInfo = "Adri",
//                subInfo = "Adriana Costa",
//                subInfo2 = "Tipo Sanguíneo: O+"
//            )
//        )

        userRepository.loadAll()?.forEach {

            items.add(
                Item(
                    id = it.id,
                    mainInfo = it.apelido ?: it.nome,
                    subInfo = it.nome,
                    subInfo2 = "Tipo Sanguíneo: ${it.tipoSanguineo}",
                    image = it.imageId
                )
            )

            view.onLoadIntegrantes(items)
        }

    }
}