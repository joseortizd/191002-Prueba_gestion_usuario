package com.falabella.ejercicio.cliente.publisher;

import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;
@Component
public class publisher {
	   @Bean
	   @ServiceActivator(inputChannel = "myOutputChannel")
	   public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
	      return new PubSubMessageHandler(pubsubTemplate, "Mensajeria");
	   }
	   @MessagingGateway(defaultRequestChannel = "myOutputChannel")
	   public interface PubsubOutboundGateway {
	      void sendToPubsub(String text);
	   }
}
