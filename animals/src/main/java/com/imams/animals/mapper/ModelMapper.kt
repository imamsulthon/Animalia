package com.imams.animals.mapper

import com.google.gson.Gson
import com.imams.animals.model.Animal

object ModelMapper {

    fun String.asAnimal(): Animal = try {
        Gson().fromJson(this, Animal::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        Animal()
    }

    fun Animal.toJson(): String = Gson().toJson(this)

}