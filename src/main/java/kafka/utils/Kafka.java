package kafka.utils;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * This class is an extension and is used to provide connectivity to a kafka
 * as well as a producer and consumer to publish and consume messages
 * @author Nils Reichstein, MaibornWolff GmbH
 */
public class Kafka implements BeforeAllCallback{

    private String bootstrapServers;
    private String consumerGroupId;
    private KafkaProducer<String, String> producer;
    private KafkaConsumer<String, String> consumer;

    @Override
    public void beforeAll(final ExtensionContext extensionContext) {
        createProducer(bootstrapServers);
        createConsumer(bootstrapServers, consumerGroupId);
    }


    public Kafka(String bootstrapServers, String consumerGroupId) {
        this.bootstrapServers = bootstrapServers;
        this.consumerGroupId = consumerGroupId;
    }


    private void createProducer(String bootstrapServers) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.producer = new KafkaProducer<>(props);
    }


    public void publishMessageToTopic(String topic, String message) {
        ProducerRecord<String, String> producedRecord = new ProducerRecord<>(topic, message);
        this.producer.send(producedRecord);
    }


    private void createConsumer(String bootstrapServers, String consumerGroupId) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        this.consumer = new KafkaConsumer<>(properties);
    }


    public String consumeNextMessageFromTopic(String topic) {
        consumer.subscribe(Collections.singletonList(topic));
        consumer.poll(Duration.ofSeconds(10));

        AtomicLong maxTimestamp = new AtomicLong();
        AtomicReference<ConsumerRecord<String, String>> latestRecord = new AtomicReference<>();

        // get the last offsets for each partition
        consumer.endOffsets(consumer.assignment()).forEach((topicPartition, offset) -> {
            System.out.println("offset: "+offset);

            // seek to the last offset of each partition
            consumer.seek(topicPartition, (offset==0) ? offset:offset - 1);

            // poll to get the last record in each partition
            consumer.poll(Duration.ofSeconds(10)).forEach(record -> {

                // the latest record in the 'topic' is the one with the highest timestamp
                if (record.timestamp() > maxTimestamp.get()) {
                    maxTimestamp.set(record.timestamp());
                    latestRecord.set(record);
                }
            });
        });
        return latestRecord.get().value();
    }
}
