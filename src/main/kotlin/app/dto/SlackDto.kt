package app.dto

data class SlashCommandDto (
    val token: String?,
    val teamId: String?,
    val teamDomain: String?,
    val channelId: String?,
    val channelName: String?,
    val userId: String?,
    val userName: String?,
    val command: String?,
    val text: String?,
    val apiAppId: String?,
    val isEnterpriseInstall: String?,
    val responseUrl: String?,
    val triggerId: String?
)