package com.imams.animals.mapper

import com.imams.animals.model.Animal
import com.imams.animals.model.Characteristic
import com.imams.animals.model.Taxonomy
import com.imams.animals.source.remote.model.AnimalsResponse
import com.imams.animals.source.remote.model.CharacteristicsResponse
import com.imams.animals.source.remote.model.TaxonomyResponse

object ResponseMapper {

    fun AnimalsResponse.toModel() = Animal(
        name = name.orEmpty(),
        taxonomy = taxonomy?.toModel() ?: Taxonomy(),
        locations = locations,
        characteristics = characteristics?.toModel() ?: Characteristic(),
    )

    fun TaxonomyResponse.toModel() = Taxonomy(
        kingdom = kingdom.orEmpty(),
        phylum = phylum.orEmpty(),
        taxClass = taxClass.orEmpty(),
        order = order.orEmpty(),
        family = family.orEmpty(),
        genus = genus.orEmpty(),
        scientificName = scientificName.orEmpty(),
    )

    fun CharacteristicsResponse.toModel() = Characteristic(
        prey = prey.orEmpty(),
        nameOfYoung = nameOfYoung.orEmpty(),
        groupBehavior = groupBehavior.orEmpty(),
        estimatedPopulationSize = estimatedPopulationSize.orEmpty(),
        biggestThreat = biggestThreat.orEmpty(),
        mostDistinctiveFeature = mostDistinctiveFeature.orEmpty(),
        gestationPeriod = gestationPeriod.orEmpty(),
        litterSize = litterSize.orEmpty(),
        habitat = habitat.orEmpty(),
        predators = predators.orEmpty(),
        diet = diet.orEmpty(),
        type = type.orEmpty(),
        commonName = commonName.orEmpty(),
        numberOfSpecies = numberOfSpecies.orEmpty(),
        location = location.orEmpty(),
        color = color.orEmpty(),
        skinType = skinType.orEmpty(),
        topSpeed = topSpeed.orEmpty(),
        lifespan = lifespan.orEmpty(),
        weight = weight.orEmpty(),
        length = length.orEmpty(),
        ageOfSexualMaturity = ageOfSexualMaturity.orEmpty(),
    )

}