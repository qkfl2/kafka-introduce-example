import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerMain {
    public static void main(String[] args) throws InterruptedException {
        try {
            Properties props = new Properties();
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group-01");
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9192");
            props.put(ConsumerConfig.CLIENT_ID_CONFIG, "test-consumer-1");
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList("tt1"));
            while (true) {
                ConsumerRecords<String, String> poll = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : poll) {
                    System.out.println(record);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
