package com.mobdeve.minors.calculator.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.minors.calculator.item.Item
import com.mobdeve.minors.calculator.DBHelper
import com.mobdeve.minors.calculator.R
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class Adapter(private val Item: ArrayList<Item>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    lateinit var DBHelper : DBHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.computation, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = Item[position]
        holder.bindItems(Item[position])


        holder.apply {
            itemView.apply {
                val tvComputation: TextView = findViewById(R.id.tvComputation)
                val tvOutput: TextView = findViewById(R.id.tvOutput)

                tvComputation.text = parent.computation
                tvOutput.text = parent.result
            }
        }

    }

    override fun getItemCount(): Int {
        return Item.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(items: Item) {

        }
    }

}