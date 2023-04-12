package com.imams.animals.mapper

import com.imams.animals.mapper.ModelMapper.asAnimal
import com.imams.animals.mapper.ModelMapper.toJson
import com.imams.animals.model.Animal
import com.imams.animals.source.local.AnimalEntity

object EntityMapper {

    fun Animal.toEntity() = AnimalEntity(
        name = name,
        data = this.toJson(),
    )

    fun AnimalEntity.toModel(): Animal {
        return data.asAnimal()
    }

}