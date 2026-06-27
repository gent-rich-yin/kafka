package org.apache.kafka.richcli;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class RichCli {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");

        try (AdminClient admin = AdminClient.create(props)) {
            System.out.println("Connecting to Kafka cluster...");
            admin.listTopics().names().get().forEach(topic -> System.out.println("topic: " + topic));
            System.out.println("Done.");
        } catch (ExecutionException e) {
            System.err.println("Failed to list topics: " + e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupted while listing topics");
            System.exit(1);
        }
    }
}
