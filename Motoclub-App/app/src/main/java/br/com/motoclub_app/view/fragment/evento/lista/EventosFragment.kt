package br.com.motoclub_app.view.fragment.evento.lista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.motoclub_app.R
import br.com.motoclub_app.type.EventType
import br.com.motoclub_app.view.fragment.BaseFragment
import br.com.motoclub_app.view.fragment.Item
import br.com.motoclub_app.view.fragment.ListAdapter
import br.com.motoclub_app.view.fragment.evento.lista.contract.EventosPresenter
import br.com.motoclub_app.view.fragment.evento.lista.contract.EventosView

class EventosFragment : BaseFragment<EventosPresenter>(), EventosView {

    companion object {

        @JvmStatic
        fun newInstance(eventType: EventType) =
            EventosFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("eventType", eventType)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            val eventType = getSerializable("eventType") as EventType
            presenter.loadEvents(eventType)
        }
    }

    override fun setTitle() {
        activity?.title = "Eventos"
    }

    override fun onLoadEvents(events: MutableList<Item>) {

        val adapter = ListAdapter(events)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_list)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(view?.context)
    }
}