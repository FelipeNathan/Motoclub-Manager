package br.com.motoclub_app.view.fragment

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.motoclub_app.R
import br.com.motoclub_app.app.utils.ImageUtils
import br.com.motoclub_app.model.Motoclube
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.SwipeLayout.SwipeListener


class ListAdapter(private val items: MutableList<Item>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var onItemClickListener: (item: Item) -> Unit = { }
    var onSwipeListener: ((Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_item, parent, false)

        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    fun last(): Item? {
        return if (items.isNotEmpty())
            items.last()
        else
            null
    }

    fun add(items: Collection<Item>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        holder.mainInfo.text = item.mainInfo
        holder.subInfo.text = item.subInfo
        holder.subInfo2.text = item.subInfo2

        holder.itemView.setOnClickListener {
            if (holder.swipeLayout.openStatus != SwipeLayout.Status.Close) return@setOnClickListener
            onItemClickListener(item)
        }

        holder.swipeLayout.isSwipeEnabled = onSwipeListener != null

        holder.swipeLayout.addSwipeListener(object : SwipeListener {
            override fun onOpen(layout: SwipeLayout?) {
                onSwipeListener?.let {
                    holder.swipeLayout.close()
                    it(item)
                }
            }

            override fun onUpdate(layout: SwipeLayout?, leftOffset: Int, topOffset: Int) {}

            override fun onStartOpen(layout: SwipeLayout?) {}

            override fun onStartClose(layout: SwipeLayout?) {}

            override fun onHandRelease(layout: SwipeLayout?, xvel: Float, yvel: Float) {}

            override fun onClose(layout: SwipeLayout?) {}

        })

        item.image?.apply {
            ImageUtils.loadImage(holder.itemView.context as Activity, this, holder.image)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var image: ImageView = view.findViewById(R.id.item_img)
        var mainInfo: TextView = view.findViewById(R.id.main_info)
        var subInfo: TextView = view.findViewById(R.id.sub_info)
        var subInfo2: TextView = view.findViewById(R.id.sub_info_2)
        var swipeLayout: SwipeLayout = view.findViewById(R.id.swipe_layout)

    }
}