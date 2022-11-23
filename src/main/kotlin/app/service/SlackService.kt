package app.service

import org.springframework.stereotype.Service
import com.slack.api.Slack
import com.slack.api.methods.kotlin_extension.request.chat.blocks
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
        val client = Slack.getInstance().methods(token)
        val response = client.chatPostMessage { req -> req
            .channel(channel)
            .blocks {
                section {
                    markdownText("*직원 입/퇴사 기능*")
                }
                divider()
                actions {
                    button {
                        text("신규 등록", emoji = true)
                        value("v1")
                    }
                    button {
                        text("퇴사 처리", emoji = true)
                        value("v2")
                    }
                }
            }
        }

        return "success"
    }
}
