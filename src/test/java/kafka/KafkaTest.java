package kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import kafka.utils.KafkaExtension;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * This class contains a sample tests which produces and consumes kafka messages
 * and also shows how to create a test container which runs Apache Kafka
 * @author Nils Reichstein, MaibornWolff GmbH
 */
@Testcontainers
class KafkaTest {

    //you can remove this container if you want to use an existing kafka environment
    @Container
    private static final KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));


    //if you use an existing kafka environment you have to replace getKafkaBootstrapServers() with your existing bootstrap server
    @RegisterExtension
    public static final KafkaExtension kafka = new KafkaExtension(getKafkaBootstrapServers(), "kafkaTestGroup");


    private static String getKafkaBootstrapServers() {
        kafkaContainer.start();
        return kafkaContainer.getBootstrapServers().replace("PLAINTEXT://", "");
    }


    @Test
    void kafkaProduceAndConsumeTest () {
        String testKafkaTopic = "testTopic";
        String testMessage = "This is a kafka test message";

        kafka.publishMessageToTopic(testKafkaTopic, testMessage);
        String nextMessageFromTopic = kafka.consumeNextMessageFromTopic(testKafkaTopic);

        assertThat(nextMessageFromTopic).isEqualTo(testMessage);
    }
}
