package com.example.app.presentation.ui.github.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.app.R
import com.example.app.presentation.di.scopes.PerActivity
import com.example.app.presentation.ui.github.model.Follower
import com.example.app.presentation.utils.imageloader.ImageLoader
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.view_follower_item.view.*
import javax.inject.Inject

@PerActivity
class FollowersAdapter
@Inject
constructor(private val imageLoader: ImageLoader) :
        RecyclerView.Adapter<FollowersAdapter.ViewHolder>() {

    var followerList: List<Follower> = emptyList()
    var itemClick: ((Follower) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_follower_item, parent, false),
                itemClick!!
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(followerList[position], imageLoader)
    }

    override fun getItemCount(): Int = followerList.size

    class ViewHolder(itemView: View, val itemClick: (Follower) -> Unit) :
            RecyclerView.ViewHolder(itemView) {

        val tvAvatarName: TextView = itemView.tvAvatarName
        val sdvAvatarImage: SimpleDraweeView = itemView.sdvAvatarImage

        fun bind(follower: Follower, imageLoader: ImageLoader) {
            with(follower) {
                tvAvatarName.text = login
                avatar_url.let {
                    imageLoader.loadImage(it, sdvAvatarImage)
                }
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}