package com.imams.animals.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Animal(
    var name: String,
    var taxonomy: Taxonomy,
    var locations: List<String>,
    var characteristics: Characteristic
): Parcelable
