package app.controller

import app.service.SlackService
import org.json.JSONObject
import org.springframework.http.MediaType
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.String.valueOf

@RestController
@RequestMapping("/slack")
class SlackController(
    val slackService: SlackService
) {
    @PostMapping("/user", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun userCommand(@RequestBody body: MultiValueMap<String, String>){
        val channelId = body.getFirst("channel_id").toString()
        slackService.sendMessage(channelId, "user")
    }

    @PostMapping("/interactivity", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun userInteractivity(@RequestBody body: MultiValueMap<String, String>){
        val payload = JSONObject(valueOf(body.getFirst("payload")))
        val triggerId = payload.getString("trigger_id")
        val actionValue = payload.getJSONArray("actions").getJSONObject(0).getString("value")
        val channelId = payload.getJSONObject("channel").getString("id")

        when(actionValue) {
            "signup" -> {
                slackService.openModal(triggerId, actionValue)
            } else -> {
                return
            }
        }
    }
}
