package com.ewencluley.hermes

import com.xenomachina.argparser.ArgParser

class Args(parser: ArgParser) {
    val name by parser.storing(
            "-U", "--user",
            help = "name of the user")

    val kafkaBootstrapServers by parser.storing(
            "-B", "--bootstrap-servers",
            help = "Kafka bootstrap servers to connect to")

    val kafkaTopic by parser.storing(
            "-T", "--topic",
            help = "Kafka topic name")
}