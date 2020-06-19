package com.sergiomartinrubio.jmsexample.pointtopoint;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.BasicConfigurator;

import javax.jms.*;
import java.util.logging.Logger;

import static org.apache.activemq.ActiveMQConnection.DEFAULT_BROKER_URL;

public class Consumer {

    private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());

    public static void main(String[] args) throws JMSException {
        // log4j configuration
        BasicConfigurator.configure();

        // Getting JMS connection from the server
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);

        try (Connection connection = connectionFactory.createConnection()) {
            connection.start();

            // Create session for receiving messages
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Getting the queue
            Destination destination = session.createQueue("example.MyQueue");
            MessageConsumer consumer = session.createConsumer(destination);
            // This blocks indefinitely until a message is produced
            Message message = consumer.receive();

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                LOGGER.info("Receive message: " + textMessage.getText());
            }
        }

    }
}
