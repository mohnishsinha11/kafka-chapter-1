package com.codefarm;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class EventConsumer {
    /* steps:
      1. To setup the configs - broker, serializer(key, value)4
      2. To create consumer client
      3. subscribe to topics
      4. poll for the message
      5. process the message
    */
    static void receiveEvent() throws InterruptedException {
        //step1: setup configs
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "codefarm");

        //step2: create consumer client
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        //step3: subscribe to the topics
        consumer.subscribe(Arrays.asList("my-first-topic"));

        while(true){
            //step4: poll for the message
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            //step5: process the records
            System.out.println("Receiving Messages : ");
            for(ConsumerRecord<String, String> record : records){
                System.out.println(record.key() + " : " + record.value());
            }
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        receiveEvent();
    }
}
