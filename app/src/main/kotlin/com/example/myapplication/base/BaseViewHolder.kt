package com.example.myapplication.base

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<in D>(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(item: D)
}