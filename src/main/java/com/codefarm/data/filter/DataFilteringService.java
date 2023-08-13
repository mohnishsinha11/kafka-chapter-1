package com.codefarm.data.filter;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class DataFilteringService {
    public static void main(String[] args) throws IOException {
        String file = "C:\\Users\\mohni\\IdeaProjects\\kafka-chapter-1\\data.txt";

        KafkaProducer<String, String> kafkaProducer = getKafkaProducer();

        List<String> tweets = Files.readAllLines(Paths.get(file));
        tweets.stream().forEach(tweet -> {
            if(tweet.toLowerCase().contains("microservice")){
                //put to microservice topic
                ProducerRecord<String, String> record = new ProducerRecord<>("microservice-topic", tweet);
                kafkaProducer.send(record);
            } else if (tweet.toLowerCase().contains("kafka")) {
                //put to kafka topic
                ProducerRecord<String, String> record = new ProducerRecord<>("kafka-topic", tweet);
                kafkaProducer.send(record);
            } else if (tweet.toLowerCase().contains("chatgpt")) {
                //put to chatgpt topic
                ProducerRecord<String, String> record = new ProducerRecord<>("chatgpt-topic", tweet);
                kafkaProducer.send(record);
            } else {
                //put to other topic
                ProducerRecord<String, String> record = new ProducerRecord<>("other-topic", tweet);
                kafkaProducer.send(record);
            }
        });

        kafkaProducer.flush();
        kafkaProducer.close();
    }

    private static KafkaProducer<String, String> getKafkaProducer() {
        Properties kafkaProducerConfig = new Properties();
        kafkaProducerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaProducerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProducerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(kafkaProducerConfig);
        return kafkaProducer;
    }
}
