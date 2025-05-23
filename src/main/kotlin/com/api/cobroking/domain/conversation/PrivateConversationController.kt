package com.api.cobroking.domain.conversation

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/private-conversations")
class PrivateConversationController(private val privateConversationService: PrivateConversationService) {


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createPrivateConversation(@RequestBody privateConversationDto: PrivateConversationDto): PrivateConversationDto {
        return privateConversationService.create(privateConversationDto)
    }

   @GetMapping("/{id}")
    @ResponseBody
    fun getPrivateConversation(@RequestParam id: Long): PrivateConversationDto {
        return privateConversationService.getById(id)
    }

    //TODO: hacer bien el mapping del tipo /privateConversations/{id}/messages/{id}

    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun sendMessage(@RequestBody privateMessage: PrivateMessageDto): PrivateMessageDto {
        return privateConversationService.sendMessage(privateMessage)
    }

    @PutMapping("/messages/{id}")
    @ResponseBody
    fun editMessage(@RequestParam id: Long, @RequestBody privateMessage: PrivateMessageDto): PrivateMessageDto {
        return privateConversationService.editMessage(privateMessage)
    }

    @DeleteMapping("/messages/{id}")
    @ResponseBody
    fun deleteMessage(@RequestParam id: Long): PrivateMessageDto {
        return privateConversationService.deleteMessage(id)
    }
}