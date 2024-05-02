package com.api.cobroking.domain.user

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
@RequestMapping("/users")
class UserController(val userService : UserService) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createUser(@RequestBody userDto: UserDto): UserDto {
        return userService.create(userDto);
    }

    @PutMapping()
    @ResponseBody
    fun updateUser(@RequestParam id: Long, @RequestBody userDto: UserDto): UserDto {
        return userService.update(id, userDto);
    }

    @GetMapping()
    @ResponseBody
    fun getUser(@RequestParam id: Long): UserDto {
        return userService.getById(id);
    }

    @GetMapping()
    @ResponseBody
    fun getAllUsers(): List<UserDto> {
        return userService.getAll();
    }
}