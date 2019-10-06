package com.falabella.ejercicio;

import com.falabella.ejercicio.data.entity.UserEntity;
import com.falabella.ejercicio.data.repository.UserRepository;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
public class EjercicioApplication {//implements CommandLineRunner{

    @Autowired
    UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(EjercicioApplication.class, args);
	}
	
/**
    @Override
    public void run(String... args) throws Exception {
        System.err.println(new Date());
        Faker faker = new Faker();
        List<UserEntity> list = new ArrayList();
        for (int iteration=0; iteration <=100; iteration++) {
            for (int i=1; i <= 10000; i++) {
                UserEntity userEntity = new UserEntity();
                userEntity.setName(faker.name().firstName());
                userEntity.setLastName(faker.name().lastName());
                userEntity.setEmail(faker.bothify("??????###@gmail.com"));
                userEntity.setDocument(faker.number().digits(200).toString());
                userEntity.setCreatedAt(new Date());
                userEntity.setAddress(faker.address().fullAddress());
                userEntity.setPhoneNumber(faker.phoneNumber().cellPhone());
                userEntity.setStatus(true);
                userEntity.setUpdatedAT(new Date());
                userEntity.setCreatedAt(new Date());
                list.add(userEntity);
            }        
            userRepository.saveAll(list);            
        }

        System.err.println(new Date());
    }

**/
}
