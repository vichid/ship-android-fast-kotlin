package com.example.myapplication.ghrepositories

import android.view.View
import com.example.myapplication.R
import com.example.myapplication.base.BaseAdapter
import com.example.myapplication.ghrepositories.model.GHRepository

class RepositoryAdapter(
    private val itemClick: (GHRepository) -> Unit
) : BaseAdapter<GHRepository, RepositoryViewHolder>() {

    override fun getItemViewId(): Int = R.layout.row_repository

    override fun instantiateViewHolder(view: View): RepositoryViewHolder =
        RepositoryViewHolder(view, itemClick)
}
