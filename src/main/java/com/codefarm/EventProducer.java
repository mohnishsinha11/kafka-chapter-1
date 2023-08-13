package com.codefarm;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class EventProducer {

    /* steps:
      1. To setup the configs - broker, serializer(key, value)4
      2. To create the record
      3. To create producer client
      4. send the record to cluster
      5. flush and close
    */
    boolean sendEvent(String message){


        //step1: setup configs
        Properties kafkaProducerConfig = new Properties();
        kafkaProducerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaProducerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProducerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        //step2: create record
        ProducerRecord<String, String> record = new ProducerRecord<>("my-first-topic", message);

        //step3: create kafka producer interface
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kafkaProducerConfig);

        //step4: send the record to cluster
        producer.send(record);

        //step5:flush and close
        producer.flush();
        producer.close();

        return true;
    }
}
