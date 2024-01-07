package com.example.homework18.data.mapper

import com.example.homework18.data.model_dtos.SupportDto
import com.example.homework18.domain.model_entities.Support

fun SupportDto.toDomain(): Support {
    return Support(
        url = url,
        text = text
    )
}