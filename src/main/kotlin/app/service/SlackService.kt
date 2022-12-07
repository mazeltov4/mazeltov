package app.service

import org.springframework.stereotype.Service
import com.slack.api.Slack
import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.kotlin_extension.block.withBlocks
import com.slack.api.model.kotlin_extension.view.blocks
import com.slack.api.model.view.View
import com.slack.api.model.view.Views.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource("classpath:slack.properties")
@Service
class SlackService {
    @Value("\${slack.token}")
    lateinit var token: String

    fun sendMessage(channelId: String, type: String = "") {
        val client = Slack.getInstance().methods(token)
        client.chatPostMessage { req -> req
            .channel(channelId)
            .blocks(
                when(type) {
                    "user" -> {userCommandBlock()}
                    else -> {userCommandBlock()}
                }
            )
        }
    }

    fun openModal(triggerId: String, type: String = "") {
        val client = Slack.getInstance().methods(token)
        client.viewsOpen{it
            .triggerId(triggerId)
            .view(
                when(type) {
                    "signup" -> {signupView()}
                    else -> {signupView()}
                }
            )
        }
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

    fun signupView(): View {
        // ---------- 임시 리스트 ---------------------
        val orgList = listOf("조직1", "조직2", "조직3", "조직4", "조직5")
        // ---------- 임시 리스트 ---------------------

        return view { thisView -> thisView
            .callbackId("signup-modal")
            .type("modal")
            .notifyOnClose(true)
            .title(viewTitle { it.type("plain_text").text("Meeting Arrangement").emoji(true) })
            .submit(viewSubmit { it.type("plain_text").text("Submit").emoji(true) })
            .close(viewClose { it.type("plain_text").text("Cancel").emoji(true) })
            .blocks {
                input {
                    plainTextInput {
                        actionId("option1")
                        placeholder("작성")
                    }
                    label("제목 타이틀 여기1", emoji = true)
                }
                divider()
                input {
                    plainTextInput {
                        actionId("option2")
                        placeholder("작성")
                    }
                    label("제목 타이틀 여기2", emoji = true)
                }
                divider()
                section {
                    markdownText("dropdown list 선택하기")
                    accessory {
                        staticSelect {
                            placeholder("조직도 선택")
                            options {
                                for (orgName in orgList) {
                                    option {
                                        plainText(orgName)
                                        value(orgName)
                                    }
                                }
                            }
                        }
                    }
                }
                divider()
                section {
                    markdownText("dropdown list 선택하기")
                    accessory {
                        staticSelect {
                            placeholder("권한 선택")
                            options {
                                for (orgName in orgList) {
                                    option {
                                        plainText(orgName)
                                        value(orgName)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
