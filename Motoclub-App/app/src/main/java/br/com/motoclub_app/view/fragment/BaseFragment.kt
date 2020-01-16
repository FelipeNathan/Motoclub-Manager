package br.com.motoclub_app.view.fragment

import android.os.Bundle
import android.view.View
import br.com.motoclub_app.core.contract.Presenter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T : Presenter> : DaggerFragment() {

    @Inject
    lateinit var presenter: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle()
    }

    abstract fun setTitle()
}