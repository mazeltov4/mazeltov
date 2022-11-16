package app.service

import org.springframework.stereotype.Service
import com.slack.api.Slack
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource("classpath:slack.properties")
@Service
class SlackService {
    @Value("\${slack.token}")
    lateinit var token: String

    @Value("\${slack.channel}")
    lateinit var channel: String

    fun sendMessage(): String{
        val client = Slack.getInstance().methods()

        runCatching {
            client.chatPostMessage {
                it.token(token)
                    .channel(channel)
                    .text("Are you mazeltov?")
            }
        }.onFailure { e ->
            return String.format("Slack Error {}", e)
        }

        return "success"
    }
}
