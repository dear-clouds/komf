package snd.komf.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import snd.komf.api.notifications.KomfDiscordTemplates
import snd.komf.api.notifications.KomfNotificationContext
import snd.komf.api.notifications.KomfRenderRequest

class KomfNotificationClient(
    private val ktor: HttpClient,
) {
    suspend fun getTemplates(): KomfDiscordTemplates {
        return ktor.get("/api/notifications/discord/templates").body()
    }

    suspend fun updateTemplates(templates: KomfDiscordTemplates): KomfDiscordTemplates {
        return ktor.post("/api/notifications/discord/templates") {
            contentType(ContentType.Application.Json)
            setBody(templates)
        }.body()
    }

    suspend fun render(request: KomfRenderRequest) {
        ktor.post("/api/notifications/discord/render") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    suspend fun send(context: KomfNotificationContext) {
        ktor.post("/api/notifications/discord/send") {
            contentType(ContentType.Application.Json)
            setBody(context)
        }
    }
}