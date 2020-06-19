package com.sergiomartinrubio.jmsexample.publishersubscriber;

import org.apache.log4j.BasicConfigurator;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Subscriber {

    public static void main(String[] args) throws NamingException, JMSException {
        // log4j configuration
        BasicConfigurator.configure();

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
        subscriber.setMessageListener(new MyListener());
    }

}
