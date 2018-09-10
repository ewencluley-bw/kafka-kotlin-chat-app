package com.ewencluley.hermes.consumer

import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration

class Consumer(
        private val userId: String,
        private val topic:String,
        properties:Map<String, Any>
) {
    private val consumer = KafkaConsumer<String, String>(properties)

    fun listen() {
        consumer.subscribe(arrayListOf(topic))
        while (true) {
            val records = consumer.poll(Duration.ofMillis(100))
            for (record in records) {
                val senderId = record.key()
                if(!senderId.equals(userId)){
                    val message = record.value()
                    println("$senderId: $message")
                }
            }
        }
    }


}