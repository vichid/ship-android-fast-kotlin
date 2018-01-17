package com.example.myapplication.ghrepositories.model

import android.os.Parcel
import com.example.myapplication.base.Identifiable
import com.example.myapplication.util.KParcelable
import com.example.myapplication.util.parcelableCreator
import com.google.gson.annotations.SerializedName

/**
 * Using name/owner_login as primary key instead of id since name/owner_login is always available
 * vs id is not.
 */
data class GHRepository(
    val id: Int,
    val name: String,
    @SerializedName("full_name")
    val fullName: String?,
    val description: String?,
    @SerializedName("owner")
    val ghPerson: GHPerson,
    @SerializedName("stargazers_count")
    val stars: Int
) : KParcelable, Identifiable<String> {

    override fun key(): String = name + "_" + ghPerson.login

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::GHRepository)
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable<GHPerson>(GHPerson::class.java.classLoader),
        parcel.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(name)
            writeString(fullName ?: "")
            writeString(description ?: "")
            writeParcelable(ghPerson, flags)
            writeInt(stars)
        }
    }
}