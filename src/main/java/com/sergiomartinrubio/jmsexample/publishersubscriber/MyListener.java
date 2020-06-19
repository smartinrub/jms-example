package com.sergiomartinrubio.jmsexample.publishersubscriber;

import com.sergiomartinrubio.jmsexample.pointtopoint.Producer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

public class MyListener implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());

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
