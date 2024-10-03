package com.example.tugasmembuataplikasiandroidpemula

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Friend(
    val name: String?,
    val description: String?,
    val photo: Int,
    val data : String?,
    val classes : String?,
    val divisi : String?,
    val motto : String?,
    val instagram : String?,
) : Parcelable