package com.ewencluley.hermes

import com.ewencluley.hermes.consumer.Consumer
import com.ewencluley.hermes.producer.Producer
import com.xenomachina.argparser.ArgParser
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.concurrent.thread

fun main(args: Array<String>) {

    ArgParser(args).parseInto(::Args).run {
        val consumer = Consumer(name, kafkaTopic, mapOf(
                "bootstrap.servers" to kafkaBootstrapServers,
                "retries" to 0,
                "group.id" to md5(name),
                "auto.offset.reset" to "earliest",
                "enable.auto.commit" to "false",
                "key.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
                "value.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer"
        ))

        val producer = Producer(name, kafkaTopic, mapOf(
                "bootstrap.servers" to kafkaBootstrapServers,
                "retries" to 0,
                "key.serializer" to "org.apache.kafka.common.serialization.StringSerializer",
                "value.serializer" to "org.apache.kafka.common.serialization.StringSerializer"
        ))
        println("You are logged in as ${name}\n\n")
        thread {
            consumer.listen()
        }

        while (true) {
            val message = readLine()
            if (message != null) {
                producer.sendMessage(message)
            }
        }
    }
}

fun md5(input:String): String {
    val messageDigest = MessageDigest.getInstance("MD5")
    messageDigest.update(input.toByteArray())
    return BigInteger(1, messageDigest.digest()).toString()
}