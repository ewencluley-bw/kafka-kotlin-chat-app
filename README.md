Kafka Chat app
-

Very basic chat app writen in Kotlin at uses a Kafka topic to store and deliver the chat room messages.

This was written as a learning exercise for Kotlin/Kafka.

Usage:

```bash
java -jar kafka-chat-app-1.0.jar -U <<your_name>> -B <<kafka_bootstrap_server(s)>> -T <<kafka_topic>>

``` 