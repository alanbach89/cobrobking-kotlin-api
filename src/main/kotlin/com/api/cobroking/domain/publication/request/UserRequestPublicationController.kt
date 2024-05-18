package com.api.cobroking.domain.publication.request

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/publications/user-requests")
class UserRequestPublicationController(val userRequestPublicationService : UserRequestPublicationService) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createPublication(@RequestBody publication: UserRequestPublicationDto): UserRequestPublicationDto {
        return userRequestPublicationService.create(publication)
    }

    @PutMapping("/{id}")
    @ResponseBody
    fun updatePublication(@RequestParam id: Long, @RequestBody publication: UserRequestPublicationDto): UserRequestPublicationDto {
        return userRequestPublicationService.update(id, publication)
    }

   @GetMapping("/{id}")
    @ResponseBody
    fun getPublication(@RequestParam id: Long): UserRequestPublicationDto {
        return userRequestPublicationService.getById(id)
    }

    @GetMapping()
    @ResponseBody
    fun getAllPublications(): List<UserRequestPublicationDto> {
        return userRequestPublicationService.getAll()
    }
}