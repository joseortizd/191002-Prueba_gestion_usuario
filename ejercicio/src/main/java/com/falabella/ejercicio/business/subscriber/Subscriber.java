package com.falabella.ejercicio.business.subscriber;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import com.falabella.ejercicio.business.service.PubSubService;
import com.falabella.ejercicio.client.dto.UserDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class Subscriber {
	
	@Autowired
	PubSubService pubSubService = new PubSubService();

	   @Bean
	   public PubSubInboundChannelAdapter messageChannelAdapter(
	         @Qualifier("myInputChannel") MessageChannel inputChannel,
	         PubSubTemplate pubSubTemplate) {
	      PubSubInboundChannelAdapter adapter =
	            new PubSubInboundChannelAdapter(pubSubTemplate, "Mensajeria");
	      adapter.setOutputChannel(inputChannel);
	      return adapter;
	   }
	   @Bean
	   public MessageChannel myInputChannel() {
	      return new DirectChannel();
	   }
	   @ServiceActivator(inputChannel = "myInputChannel")
	   public void messageReceiver(String payload) throws IOException {
   	      ObjectMapper mapper = new ObjectMapper();
	      JsonNode node = mapper.readTree(payload);
	      UserDTO userDTO = new UserDTO();
	      userDTO.setAddress(node.get("address").asText());
	      userDTO.setDocument(node.get("document").asText());
	      userDTO.setEmail(node.get("email").asText());
	      userDTO.setLastName(node.get("lastName").asText());
	      userDTO.setName(node.get("name").asText());
	      userDTO.setPhoneNumber(node.get("phoneNumber").asText());
	      this.pubSubService.postMessageToApi(userDTO);
	      /**
	      * 
	      * 
	      */
	   }

}
