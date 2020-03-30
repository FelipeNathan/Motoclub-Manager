package br.com.motoclub_app.view.activity.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.motoclub_app.R
import br.com.motoclub_app.view.activity.BaseActivity
import br.com.motoclub_app.view.activity.login.contract.LoginPresenter
import br.com.motoclub_app.view.activity.login.contract.LoginView
import br.com.motoclub_app.view.activity.main.MainActivity
import br.com.motoclub_app.view.activity.user.UserActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener(this)
        txt_create_account.setOnClickListener(this)
    }

    override fun onLogin() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun onClick(v: View?) {

        when(v?.id) {
            btn_login.id -> signIn()
            txt_create_account.id -> startActivity(Intent(this, UserActivity::class.java))
        }
    }

    private fun signIn() {

        val email = txt_email_login.text.toString()
        val pass = txt_password_login.text.toString()

        if (email.isEmpty()) {
            showMessage("Insira o email")
            return
        }

        if (pass.isEmpty()) {
            showMessage("Insira a senha")
            return
        }

        presenter.login(email, pass)
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }
}