package io.github.thinkframework.generator.amqp;

import org.apache.qpid.server.SystemLauncher;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class EmbeddedBroker
{
    private static final String INITIAL_CONFIGURATION = "test-initial-config.json";

    public static void main(String args[]) {
        EmbeddedBroker broker = new EmbeddedBroker();
        try {
            broker.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void start() throws Exception {
        final SystemLauncher systemLauncher = new SystemLauncher();
        try {
            systemLauncher.startup(createSystemConfig());
            // performMessagingOperations();
        } finally {
            systemLauncher.shutdown();
        }
    }

    private Map<String, Object> createSystemConfig() {
        Map<String, Object> attributes = new HashMap<>();
        URL initialConfig = EmbeddedBroker.class.getClassLoader().getResource(INITIAL_CONFIGURATION);
        attributes.put("type", "Memory");
        attributes.put("initialConfigurationLocation", initialConfig.toExternalForm());
        attributes.put("startupLoggedToSystemOut", true);
        return attributes;
    }
}
