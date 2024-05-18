package com.api.cobroking.domain.publication.property

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/publications/property-offers")
class PropertyOfferPublicationController(val propertyOfferPublicationService : PropertyOfferPublicationService) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createPublication(@RequestBody publication: PropertyOfferPublicationDto): PropertyOfferPublicationDto {
        return propertyOfferPublicationService.create(publication)
    }

    @PutMapping("/{id}")
    @ResponseBody
    fun updatePublication(@RequestParam id: Long, @RequestBody publication: PropertyOfferPublicationDto): PropertyOfferPublicationDto {
        return propertyOfferPublicationService.update(id, publication)
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun getPublication(@RequestParam id: Long): PropertyOfferPublicationDto {
        return propertyOfferPublicationService.getById(id)
    }

    @GetMapping()
    @ResponseBody
    fun getAllPublications(): List<PropertyOfferPublicationDto> {
        return propertyOfferPublicationService.getAll()
    }
}