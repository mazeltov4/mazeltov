package app.service

import org.springframework.stereotype.Service
import com.slack.api.Slack
import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.kotlin_extension.block.withBlocks
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource("classpath:slack.properties")
@Service
class SlackService {
    @Value("\${slack.token}")
    lateinit var token: String

    fun sendMessage(channelId: String, type: String = ""): String{
        val client = Slack.getInstance().methods(token)
        val response = client.chatPostMessage { req -> req
            .channel(channelId)
            .blocks(
                    when(type) {
                    "user" -> {userCommandBlock()}
                    else -> {userCommandBlock()}
                }
            )
        }

        return "success"
    }

    fun userCommandBlock(): List<LayoutBlock> {
        return withBlocks {
            section {
                markdownText("*직원 입/퇴사 기능*")
            }
            divider()
            actions {
                button {
                    text("신규 등록", emoji = true)
                    value("signup")
                }
                button {
                    text("퇴사 처리", emoji = true)
                    value("withdrawal")
                }
            }
        }
    }

    fun signupBlock(): List<LayoutBlock>{
        return withBlocks {
            section {
                markdownText("*직원 신규 등록*")
            }
            divider()
            actions {
                button {
                    text("신규 등록", emoji = true)
                    value("signup")
                }
                button {
                    text("퇴사 처리", emoji = true)
                    value("withdrawal")
                }
            }
        }
    }
}
