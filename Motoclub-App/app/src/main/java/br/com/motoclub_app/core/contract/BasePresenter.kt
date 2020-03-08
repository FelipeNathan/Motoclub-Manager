package br.com.motoclub_app.core.contract

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter : Presenter {

    protected val compositeDisposable = CompositeDisposable()

    override fun onStop() {
        compositeDisposable.clear()
    }
}