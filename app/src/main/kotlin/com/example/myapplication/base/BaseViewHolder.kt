package com.example.myapplication.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

abstract class BaseViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
