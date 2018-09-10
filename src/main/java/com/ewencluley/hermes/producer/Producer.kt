package com.ewencluley.hermes.producer

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

class Producer(
        private val userId: String,
        private val topic:String,
        properties: Map<String, Any>
) {
    val producer = KafkaProducer<String, String>(properties)

    fun sendMessage(message: String) {
        producer.send(ProducerRecord(topic, userId, message))
    }
}