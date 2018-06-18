package com.techmisal.producer;

import com.techmisal.StreamGenerator;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class AvroStreamProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long events = 100;

        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                KafkaAvroSerializer.class.getName());
        props.put("schema.registry.url", "http://localhost:8081");

        KafkaProducer<String, StreamGenerator> producer = new KafkaProducer<String, StreamGenerator>(props);
        String topic = "test";


        for (long nEvents = 0; nEvents < events; nEvents++) {


           StreamGenerator event = StreamValuesGenerator.getNext();

           ProducerRecord<String, StreamGenerator> record = new ProducerRecord<String, StreamGenerator>(topic,event.getId().toString(),event);

           producer.send(record);

            System.out.println("Record sent "+nEvents);

        }

        producer.close();


    }



}
