package com.sergiomartinrubio.jmsexample.publishersubscriber;

import com.sergiomartinrubio.jmsexample.pointtopoint.Producer;
import org.apache.log4j.BasicConfigurator;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Logger;

public class Publisher {

    private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());

    public static void main(String[] args) throws NamingException, JMSException {
        // log4j configuration
        BasicConfigurator.configure();

        // Obtain a JNDI connection
        InitialContext jndi = new InitialContext();

        // Look up a JMS connection factory
        TopicConnectionFactory connectionFactory = (TopicConnectionFactory) jndi.lookup("TopicConnectionFactory");

        // Create a JMS connection and start the JMS connection; allows messages to be received
        TopicConnection connection = connectionFactory.createTopicConnection();
        connection.start();

        // Create JMS session publisher
        TopicSession publisherSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        // Look up a JMS topic
        Topic topic = (Topic) jndi.lookup("MyTopic");

        // Create JMS publisher
        TopicPublisher publisher = publisherSession.createPublisher(topic);

        // Create and send message using topic publisher
        TextMessage message = publisherSession.createTextMessage("How are you my friend?");
        publisher.publish(message);
        LOGGER.info("Message " + message.getText() + " was published to topic " + topic.getTopicName());
    }
}
