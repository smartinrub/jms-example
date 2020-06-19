package com.sergiomartinrubio.jmsexample.pointtopoint;

import org.apache.log4j.BasicConfigurator;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Logger;


public class Producer {

    private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());

    public static void main(String[] args) throws JMSException, NamingException {
        // log4j configuration
        BasicConfigurator.configure();

        // Obtain a JNDI connection
        InitialContext jndi = new InitialContext();

        // Look up a JMS connection factory
        ConnectionFactory connectionFactory = (ConnectionFactory) jndi.lookup("ConnectionFactory");

        try (Connection connection = connectionFactory.createConnection()) {
            connection.start();

            // Create session to send a receive messages. Set the first parameter to true
            // if you want to allow transactions
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = (Destination) jndi.lookup("MyQueue");

            // To send messages
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage("Hello World!");
            producer.send(message);
            LOGGER.info("Message " + message.getText() + " was sent!");
        }
    }
}
