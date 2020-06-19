package com.sergiomartinrubio.jmsexample.publishersubscriber;

import com.sergiomartinrubio.jmsexample.pointtopoint.Producer;
import org.apache.log4j.BasicConfigurator;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Logger;

public class Subscriber implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());

    public static void main(String[] args) throws NamingException, JMSException {
        // log4j configuration
        BasicConfigurator.configure();

        new Subscriber();
    }

    public Subscriber() throws JMSException, NamingException {
        // Obtain a JNDI connection
        InitialContext jndi = new InitialContext();

        // Look up a JMS connection factory
        TopicConnectionFactory connectionFactory = (TopicConnectionFactory) jndi.lookup("TopicConnectionFactory");

        // Create a JMS connection and start the JMS connection; allows messages to be delivered
        TopicConnection connection = connectionFactory.createTopicConnection();
        connection.start();

        // Create JMS session publisher
        TopicSession subscriberSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        // Look up a JMS topic
        Topic topic = (Topic) jndi.lookup("MyTopic");

        // Create a JMS subscriber
        TopicSubscriber subscriber = subscriberSession.createSubscriber(topic);

        // Set a JMS message listener
        subscriber.setMessageListener(this);
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            LOGGER.info("Receive message: " + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
