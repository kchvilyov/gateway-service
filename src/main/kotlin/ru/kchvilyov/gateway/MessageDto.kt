package ru.kchvilyov.gateway

import java.time.Instant

data class MessageDto(
    val userId: String,
    val text: String,
    val timestamp: Instant = Instant.now()
)