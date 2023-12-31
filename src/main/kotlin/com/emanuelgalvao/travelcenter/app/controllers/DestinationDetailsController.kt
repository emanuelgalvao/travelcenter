package com.emanuelgalvao.travelcenter.app.controllers

import com.emanuelgalvao.travelcenter.app.dto.DestinationDetailsDataDTO
import com.emanuelgalvao.travelcenter.app.dto.DestinationDetailsInfoDTO
import com.emanuelgalvao.travelcenter.exceptions.RegisterNotFoundException
import com.emanuelgalvao.travelcenter.repositories.DestinationAttractionRepository
import com.emanuelgalvao.travelcenter.repositories.DestinationRatingRepository
import com.emanuelgalvao.travelcenter.repositories.DestinationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/app")
class DestinationDetailsController {

    @Autowired
    lateinit var destinationRepository: DestinationRepository

    @Autowired
    lateinit var destinationAttractionRepository: DestinationAttractionRepository

    @Autowired
    lateinit var ratingRepository: DestinationRatingRepository

    @GetMapping("/destinationDetails/{id}")
    fun getDestinationDetails(@PathVariable("id") id: String): Any {
        return try {
            val destination = destinationRepository.findById(id).orElseThrow {
                RegisterNotFoundException("Destino não encontrado.")
            }

            val attractions = destinationAttractionRepository.findByDestinationId(id)

            val ratings = ratingRepository.findByDestinationId(id)

            DestinationDetailsDataDTO(
                destinationInfo = DestinationDetailsInfoDTO(
                    name = destination.name,
                    photoUrl = destination.photoUrl,
                    description = destination.description
                ),
                destinationAttractions = attractions,
                ratings = ratings
            )
        } catch (exception: Exception) {
            exception
        }
    }
}