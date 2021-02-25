import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

public class ProducerMain {
    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9192");
            props.put(ProducerConfig.CLIENT_ID_CONFIG, "test-producer-1");
            props.put(ProducerConfig.ACKS_CONFIG, "-1");
            props.put(ProducerConfig.RETRIES_CONFIG, 3);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

            Producer<String, String> producer = new KafkaProducer<>(props);

            while (true) {
                producer.send(new ProducerRecord("tt1", "value"));
                System.in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
