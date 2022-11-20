package app.controller

import app.dto.SlashCommandDto
import org.modelmapper.ModelMapper
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController {
    @PostMapping("", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun userCommand(@RequestBody temp: String) {
        val tempData = temp.split("&")
        val commandData = mutableMapOf<String, String>()
        for(data in tempData){
            val tempSplit = data.split("=")
            commandData[tempSplit[0]] = tempSplit[1]
        }

        val commandDto = SlashCommandDto(
            token=commandData["token"],
            teamId=commandData["team_id"],
            teamDomain=commandData["team_domain"],
            channelId=commandData["channel_id"],
            channelName=commandData["channel_name"],
            userId=commandData["user_id"],
            userName=commandData["user_name"],
            command=commandData["command"],
            text=commandData["text"],
            apiAppId=commandData["api_app_id"],
            isEnterpriseInstall=commandData["is_enterprise_install"],
            responseUrl=commandData["response_url"],
            triggerId=commandData["trigger_id"],
        )
    }
}
