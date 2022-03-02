package com.kay.prog.exam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.exam.database.Character

class Adapter(
    private val click: (id: Long) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var list: List<Character> = listOf()

    fun setData(list: List<Character>) {
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

        fun bind(character: Character) {
            val data = itemView.findViewById<AppCompatTextView>(R.id.itemData)
            data.text = character.name

            itemView.setOnClickListener {
                click.invoke(character.id)
            }
        }
    }
}