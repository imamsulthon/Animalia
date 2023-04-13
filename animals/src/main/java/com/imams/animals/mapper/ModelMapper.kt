package com.imams.animals.mapper

import com.google.gson.Gson
import com.imams.animals.model.Animal
import com.imams.animals.model.Characteristic
import com.imams.animals.model.Taxonomy

object ModelMapper {

    fun String.asAnimal(): Animal = try {
        Gson().fromJson(this, Animal::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        Animal()
    }

    fun Animal.toJson(): String = Gson().toJson(this)

    fun Taxonomy.toLabel(): String {
        return "kingdom: $kingdom \n" +
                "phylum: $phylum \n" +
                "class: $taxClass \n" +
                "order: $order \n" +
                "family: $family \n" +
                "genus: $genus \n" +
                "scientific name: $scientificName"
    }

    fun Characteristic.toLabel(): String {
        return "prey: $prey \n" +
                "name of young: $nameOfYoung \n" +
                "group Behavior: $groupBehavior \n" +
                "estimated Population Size: $estimatedPopulationSize \n" +
                "biggest Threat: $biggestThreat \n" +
                "most Distinctive Feature: $mostDistinctiveFeature \n" +
                "gestation period: $gestationPeriod" +
                "litter Size: $litterSize \n" +
                "habitat: $habitat \n" +
                "predators: $predators \n" +
                "diet: $diet \n" +
                "type: $type \n" +
                "common Name: $commonName \n" +
                "number Of Species: $numberOfSpecies"
    }

}