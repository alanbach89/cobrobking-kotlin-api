package com.api.cobroking.domain.conversation

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/privateConversations")
class PrivateConversationController(private val privateConversationService: PrivateConversationService) {


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createPrivateConversation(@RequestBody privateConversationDto: PrivateConversationDto): PrivateConversationDto {
        return privateConversationService.create(privateConversationDto)
    }

    @GetMapping()
    @ResponseBody
    fun getPrivateConversation(@RequestParam id: Long): PrivateConversationDto {
        return privateConversationService.getById(id)
    }

    //TODO: hacer bien el mapping del tipo /privateConversations/{id}/messages/{id}

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun sendMessage(@RequestBody privateMessage: PrivateMessageDto): PrivateMessageDto {
        return privateConversationService.sendMessage(privateMessage)
    }

    @PutMapping()
    @ResponseBody
    fun editMessage(@RequestParam id: Long, @RequestBody privateMessage: PrivateMessageDto): PrivateMessageDto {
        return privateConversationService.editMessage(privateMessage)
    }

    @DeleteMapping()
    @ResponseBody
    fun deleteMessage(@RequestParam id: Long): PrivateMessageDto {
        return privateConversationService.deleteMessage(id)
    }
}