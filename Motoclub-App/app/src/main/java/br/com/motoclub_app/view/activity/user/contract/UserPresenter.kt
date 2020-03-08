package br.com.motoclub_app.view.activity.user.contract

import br.com.motoclub_app.core.contract.Presenter
import br.com.motoclub_app.model.User

interface UserPresenter : Presenter {

    fun save(user: User)

    fun loadById(id: String)
}