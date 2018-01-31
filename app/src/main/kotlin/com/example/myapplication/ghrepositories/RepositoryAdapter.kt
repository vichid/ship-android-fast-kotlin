package com.example.myapplication.ghrepositories

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.example.myapplication.R
import com.example.myapplication.base.BaseAdapter
import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.util.GlideApp

class RepositoryAdapter(
    private val context: Context,
    private val viewPreloadSizeProvider: ViewPreloadSizeProvider<GHRepository>,
    private val itemClick: (GHRepository) -> Unit
) : BaseAdapter<GHRepository, RepositoryViewHolder>(), ListPreloader.PreloadModelProvider<GHRepository> {

    override fun getItemViewId(): Int = R.layout.row_repository

    override fun instantiateViewHolder(view: View): RepositoryViewHolder =
        RepositoryViewHolder(view, itemClick)

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        viewPreloadSizeProvider.setView(holder.ivAvatar)
    }

    override fun getPreloadItems(position: Int): List<GHRepository> = listOf(sourceList[position])

    @SuppressLint("CheckResult")
    override fun getPreloadRequestBuilder(item: GHRepository): RequestBuilder<Drawable> = GlideApp.with(context)
        .load(item.ghPerson.avatarUrl)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
}
