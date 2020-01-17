package br.com.motoclub_app.view.fragment.integrante.lista

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.motoclub_app.R
import br.com.motoclub_app.view.activity.user.UserActivity
import br.com.motoclub_app.view.fragment.BaseFragment
import br.com.motoclub_app.view.fragment.Item
import br.com.motoclub_app.view.fragment.ListAdapter
import br.com.motoclub_app.view.fragment.integrante.lista.contract.IntegrantesPresenter
import br.com.motoclub_app.view.fragment.integrante.lista.contract.IntegrantesView
import com.afollestad.vvalidator.form
import kotlinx.android.synthetic.main.fragment_list.*

class IntegrantesFragment : BaseFragment<IntegrantesPresenter>(), IntegrantesView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadIntegrantes()

        refresh_layout.setOnRefreshListener { presenter.loadIntegrantes() }
        list_fab.setOnClickListener { startActivity(Intent(view.context, UserActivity::class.java)) }
    }

    override fun setTitle() {
        activity?.title = "Integrantes"
    }

    override fun onLoadIntegrantes(integrantes: MutableList<Item>) {

        val adapter = ListAdapter(integrantes)

        adapter.onItemClickListener = {

            val userIntent = Intent(context, UserActivity::class.java)
            userIntent.putExtra("id", it.id)
            userIntent.putExtra("readOnly", true)
            startActivity(userIntent)
        }

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_list)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(view?.context)

        refresh_layout.isRefreshing = false
    }
}
