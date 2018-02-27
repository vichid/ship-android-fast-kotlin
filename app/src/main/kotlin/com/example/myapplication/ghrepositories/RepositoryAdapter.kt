package com.example.myapplication.ghrepositories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.BR
import com.example.myapplication.base.BaseViewHolder
import com.example.myapplication.base.Identifiable
import com.example.myapplication.databinding.ViewHolderItemRepositoryBinding
import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.util.AutoUpdatableAdapter
import kotlin.properties.Delegates

class RepositoryAdapter(private val itemClick: (GHRepository) -> Unit)
    : RecyclerView.Adapter<BaseViewHolder>(), AutoUpdatableAdapter {

    var sourceList: List<GHRepository> by Delegates.observable(ArrayList()) { _, old, new ->
        autoNotify(old, new) { o, n -> (o as? Identifiable<*>)?.key() == (n as? Identifiable<*>)?.key() }
    }

    override fun getItemViewType(position: Int): Int = VIEWHOLDER_REPOSITORY

    override fun getItemCount(): Int = sourceList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder = when (viewType) {
        VIEWHOLDER_REPOSITORY -> RepositoryViewHolder(
            ViewHolderItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        else -> throw RuntimeException("Not implemented")
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is RepositoryViewHolder -> {
                holder.binding.setVariable(BR.repository, sourceList[position])
                holder.binding.root.setOnClickListener { itemClick.invoke(sourceList[position]) }
            }
        }
        holder.binding.executePendingBindings()
    }

    class RepositoryViewHolder(binding: ViewHolderItemRepositoryBinding) : BaseViewHolder(binding)

    companion object {
        private const val VIEWHOLDER_REPOSITORY: Int = 0
    }
}
