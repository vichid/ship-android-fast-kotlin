package com.example.myapplication.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.util.AutoUpdatableAdapter
import kotlin.properties.Delegates

abstract class BaseAdapter<D, VH : BaseViewHolder<D>> : RecyclerView.Adapter<VH>(), AutoUpdatableAdapter {

    var sourceList: List<D> by Delegates.observable(ArrayList()) { _, old, new ->
        autoNotify(old, new) { o, n -> (o as? Identifiable<*>)?.key() == (n as? Identifiable<*>)?.key() }
    }

    override fun getItemCount() = sourceList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        instantiateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(getItemViewId(), parent, false)
        )

    abstract fun getItemViewId(): Int

    abstract fun instantiateViewHolder(view: View): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }

    private fun getItem(position: Int) = sourceList[position]
}