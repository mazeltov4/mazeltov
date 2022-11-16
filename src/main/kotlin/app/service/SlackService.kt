package app.service

import org.springframework.stereotype.Service
import com.slack.api.Slack

@Service
class SlackService {
    fun sendMessage(): String{
        val client = Slack.getInstance().methods()

        runCatching {
            client.chatPostMessage {
                it.token("xoxb-2137822082532-4375778787557-22q0AICB96dQkvW34hqE62ai")
                    .channel("D04B75DT3J8")
                    .text("Are you mazeltov?")
            }
        }.onFailure { e ->
            return String.format("Slack Error {}", e)
        }

        return "success"
    }
}
