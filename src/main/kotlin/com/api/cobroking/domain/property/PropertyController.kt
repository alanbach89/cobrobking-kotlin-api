package com.api.cobroking.domain.property

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/properties")
class PropertyController(val propertyService: PropertyService) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createProperty(@RequestBody property: PropertyDto): PropertyDto {
        return propertyService.create(property)
    }

    @PutMapping("/{id}")
    @ResponseBody
    fun updateProperty(@RequestParam id: Long, @RequestBody property: PropertyDto): PropertyDto {
        return propertyService.update(id, property)
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun getProperty(@RequestParam id: Long): PropertyDto {
        return propertyService.getById(id)
    }

    @GetMapping()
    @ResponseBody
    fun getAllProperties(): List<PropertyDto> {
        return propertyService.getAll()
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    fun deleteProperty(@RequestParam id: Long) {
        return propertyService.deleteById(id)
    }
}