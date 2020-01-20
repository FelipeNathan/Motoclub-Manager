package br.com.motoclub_app.view.fragment.motoclube.lista

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.motoclub_app.R
import br.com.motoclub_app.repository.UserRepository
import br.com.motoclub_app.view.activity.motoclube.MotoclubeActivity
import br.com.motoclub_app.view.fragment.BaseFragment
import br.com.motoclub_app.view.fragment.Item
import br.com.motoclub_app.view.fragment.ListAdapter
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesFragmentPresenter
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesFragmentView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_list.*

class MotoclubesFragment : BaseFragment<MotoclubesFragmentPresenter>(), MotoclubesFragmentView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresh_layout.setOnRefreshListener { presenter.loadMotoclubes() }
        list_fab.setOnClickListener {
            startActivity(
                Intent(
                    view.context,
                    MotoclubeActivity::class.java
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()

        presenter.loadMotoclubes()

        if (UserRepository.loggedUser?.motoclubeId == null) {

            if (!list_fab.isVisible)
                list_fab.show()
        } else {

            list_fab.hide()
        }


    }

    override fun setTitle() {
        activity?.title = "Motoclubes"
    }

    override fun onLoadMotoclubes(motoclubes: MutableList<Item>) {

        val adapter = ListAdapter(motoclubes)

        adapter.onItemClickListener = {

            val intent = Intent(context, MotoclubeActivity::class.java)
            intent.putExtra("id", it.id)
            intent.putExtra("readOnly", true)
            startActivity(intent)
        }

        if (UserRepository.loggedUser!!.motoclubeId == null) {
            adapter.onSwipeListener = {
                presenter.solicitarEntrada(it.id!!)
            }
        }

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_list)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(view?.context)

        refresh_layout.isRefreshing = false
    }

    override fun showError(msg: String?) {
        Toast.makeText(context, msg ?: "Houve um erro na requisição", Toast.LENGTH_LONG).show()
    }

    override fun onSolicitarEntrada() {

        view?.apply {
            Snackbar.make(this, "Solicitação enviada com sucesso", Snackbar.LENGTH_LONG).show()
        }

        fragmentManager?.beginTransaction()
            ?.detach(this)
            ?.attach(this)
            ?.commit()
    }
}
