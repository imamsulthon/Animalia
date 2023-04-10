package com.imams.animals.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GroupAnimal(
    val group: String,
    val list: List<Animal>,
): Parcelable
