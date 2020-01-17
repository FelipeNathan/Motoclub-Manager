package br.com.motoclub_app.view.fragment.motoclube.lista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.motoclub_app.R
import br.com.motoclub_app.view.fragment.BaseFragment
import br.com.motoclub_app.view.fragment.Item
import br.com.motoclub_app.view.fragment.ListAdapter
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesPresenter
import br.com.motoclub_app.view.fragment.motoclube.lista.contract.MotoclubesView
import kotlinx.android.synthetic.main.fragment_list.*

class MotoclubesFragment : BaseFragment<MotoclubesPresenter>(), MotoclubesView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadMotoclubes()

        refresh_layout.setOnRefreshListener {
            presenter.loadMotoclubes()
        }
    }

    override fun setTitle() {
        activity?.title = "Motoclubes"
    }

    override fun onLoadMotoclubes(motoclubes: MutableList<Item>) {

        val adapter = ListAdapter(motoclubes)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_list)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(view?.context)

        refresh_layout.isRefreshing = false
    }
}
