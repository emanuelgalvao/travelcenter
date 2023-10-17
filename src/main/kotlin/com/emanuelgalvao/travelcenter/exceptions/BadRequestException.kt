package com.emanuelgalvao.travelcenter.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

data class BadRequestException(
    override val message: String
): ResponseStatusException(
    HttpStatus.BAD_REQUEST,
    message
)
