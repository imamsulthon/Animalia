package com.imams.animals.source.remote.model

import com.google.gson.annotations.SerializedName

data class AnimalsResponse(
    @SerializedName("name") var name: String? = null,
    @SerializedName("taxonomy") var taxonomy: TaxonomyResponse?,
    @SerializedName("locations") var locations: List<String> = listOf(),
    @SerializedName("characteristics") var characteristics: CharacteristicsResponse?,
)
