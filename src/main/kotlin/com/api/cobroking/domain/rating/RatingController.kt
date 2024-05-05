package com.api.cobroking.domain.rating

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ratings")
class RatingController(val ratingService : RatingService) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createRating(@RequestBody ratingDto: RatingDto): RatingDto {
        return ratingService.create(ratingDto);
    }

    /*
    @PutMapping()
    @ResponseBody
    fun updateRating(@RequestParam id: Long, @RequestBody ratingDto: RatingDto): RatingDto {
        return ratingService.update(id, ratingDto);
    }
     */

    @GetMapping()
    @ResponseBody
    fun getRating(@RequestParam id: Long): RatingDto {
        return ratingService.getById(id);
    }

    @GetMapping()
    @ResponseBody
    fun getAllRatings(): List<RatingDto> {
        return ratingService.getAll();
    }
}