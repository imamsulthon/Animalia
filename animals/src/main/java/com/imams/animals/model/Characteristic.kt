package com.imams.animals.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Characteristic(
    var prey: String,
    var nameOfYoung: String,
    var groupBehavior: String,
    var estimatedPopulationSize: String,
    var biggestThreat: String,
    var mostDistinctiveFeature: String,
    var gestationPeriod: String,
    var litterSize: String,
    var habitat: String,
    var predators: String,
    var diet: String,
    var type: String,
    var commonName: String,
    var numberOfSpecies: String,
    var location: String,
    var color: String,
    var skinType: String,
    var topSpeed: String,
    var lifespan: String,
    var weight: String,
    var length: String,
    var ageOfSexualMaturity: String,
): Parcelable {
    constructor() : this("", "", "", "", "", "","", "", "","", "", "","", "", "","", "", "","", "", "","",)
}
