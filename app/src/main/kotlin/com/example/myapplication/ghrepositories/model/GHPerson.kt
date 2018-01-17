package com.example.myapplication.ghrepositories.model

import android.os.Parcel
import com.example.myapplication.base.Identifiable
import com.example.myapplication.util.KParcelable
import com.example.myapplication.util.parcelableCreator
import com.google.gson.annotations.SerializedName

data class GHPerson(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val url: String?
) : KParcelable, Identifiable<String> {

    override fun key(): String = login

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(login)
            writeString(avatarUrl ?: "")
            writeString(url ?: "")
        }
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::GHPerson)
    }
}