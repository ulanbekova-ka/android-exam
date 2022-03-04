package com.kay.prog.exam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kay.prog.exam.api.Item

class Adapter(
    private val click: (id: Long) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var list: List<Item> = listOf()

    fun setData(list: List<Item>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(itemView, click)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(
        itemView: View, private val click: (id: Long) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item) {
            val img = itemView.findViewById<AppCompatImageView>(R.id.img)
            val name = itemView.findViewById<AppCompatTextView>(R.id.name)
            val status = itemView.findViewById<AppCompatTextView>(R.id.status)
            val species = itemView.findViewById<AppCompatTextView>(R.id.species)
//            val location = itemView.findViewById<AppCompatTextView>(R.id.location)

            Glide.with(itemView.context).load(item.image).into(img)
            name.text = item.name
            status.text = item.status
            species.text = item.species
//            location.text = item.location?.name

            itemView.setOnClickListener {
                click.invoke(item.id)
            }
        }
    }
}