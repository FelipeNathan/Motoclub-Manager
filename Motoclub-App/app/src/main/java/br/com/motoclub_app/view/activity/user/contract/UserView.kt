package br.com.motoclub_app.view.activity.user.contract

import br.com.motoclub_app.core.contract.View
import br.com.motoclub_app.model.User

interface UserView : View {

    fun onSave()

    fun showMessage(msg: String)

    fun showError(msg: String?)

    fun onLoadUser(u: User?)
}