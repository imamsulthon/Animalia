package com.imams.animals.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Taxonomy(
    var kingdom: String,
    var phylum: String,
    var taxClass: String,
    var order: String,
    var family: String,
    var genus: String,
    var scientificName: String,
): Parcelable {
    constructor(): this("", "", "","", "", "","",)
}
