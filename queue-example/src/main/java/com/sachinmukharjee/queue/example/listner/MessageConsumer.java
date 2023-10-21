package com.sachinmukharjee.queue.example.listner;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

@Component
public class MessageConsumer implements MessageListener {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

	@Override
	@JmsListener(destination = "${active-mq.topic}")
	public void onMessage(Message message) {

		try {
			LOGGER.info("Message Received {}", message);
		} catch (Exception e) {
			LOGGER.error("Exception occured while consuming message ", e);
		}
	}

}
