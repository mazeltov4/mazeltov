package app.controller

import app.service.SlackService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SlackController(
    val slackService: SlackService
) {
    @PostMapping("/slack/message")
    fun sendMessage(): String{
        return slackService.sendMessage()
    }
}

