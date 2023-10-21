package com.sachinmukharjee.queue.example.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import jakarta.jms.ConnectionFactory;

@Configuration
public class QueueConfig {

	@Value("${active-mq.broker-url}")
	private String brokerUrl;

	@Bean
	public ConnectionFactory createConnectionFactory() {
		final ActiveMQConnectionFactory activeMqConnFactory = new ActiveMQConnectionFactory();
		activeMqConnFactory.setUserName("admin");
		activeMqConnFactory.setPassword("admin");
		activeMqConnFactory.setBrokerURL(brokerUrl);
		return activeMqConnFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(createConnectionFactory());
		jmsTemplate.setPubSubNoLocal(true);
		return jmsTemplate;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(createConnectionFactory());
		factory.setPubSubDomain(true);
		return factory;
	}

}
