package br.com.motoclub_app.view.activity.user.contract

import br.com.motoclub_app.core.contract.Presenter
import br.com.motoclub_app.model.User

interface UserPresenter : Presenter {

    fun salvar(user: User)

    fun loadById(id: Long): User
}