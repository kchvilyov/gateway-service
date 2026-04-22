package ru.kchvilyov.gateway

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/messages")
class MessageController(private val kafkaTemplate: KafkaTemplate<String, String>) {

    private val objectMapper: ObjectMapper = jacksonObjectMapper()

    @PostMapping
    fun sendMessage(@RequestBody dto: MessageDto): ResponseEntity<String> {
        val json = objectMapper.writeValueAsString(dto)
        kafkaTemplate.send("user-messages", dto.userId, json).get()
        return ResponseEntity.ok("Message sent")
    }
}