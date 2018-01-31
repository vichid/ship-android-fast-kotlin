package com.example.myapplication.ghrepositories

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.base.BaseViewHolder
import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.util.ext.TransformationType
import com.example.myapplication.util.ext.load
import kotlinx.android.synthetic.main.row_repository.view.*

class RepositoryViewHolder(
    view: View,
    private val itemClick: (GHRepository) -> Unit
) : BaseViewHolder<GHRepository>(view) {

    val tvRepoTitle: TextView = view.tvRepoTitle
    val tvStars: TextView = view.tvStars
    val ivAvatar: ImageView = view.ivAvatar

    override fun onBind(item: GHRepository) {
        tvRepoTitle.text = item.name
        tvStars.text = itemView.context.resources.getQuantityString(R.plurals.stars, item.stars, item.stars)
        ivAvatar.load(item.ghPerson.avatarUrl, TransformationType.CIRCLE)
        itemView.setOnClickListener {
            itemClick.invoke(item)
        }
    }
}