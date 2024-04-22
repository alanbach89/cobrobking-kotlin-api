package com.api.cobroking.domain.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(val userService : UserService) {

    @PostMapping()
    fun createUser(@RequestBody userDto: UserDto): UserDto {
        return userService.createUser(userDto);
    }

    @GetMapping()
    fun getUser(@RequestParam id: Long): UserDto {
        return userService.getUserById(id);
    }

    @PutMapping()
    fun updateUser(@RequestParam id: Long, @RequestBody userDto: UserDto): UserDto {
        return userService.updateUser(id, userDto);
    }
}