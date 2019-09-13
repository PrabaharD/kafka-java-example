package com.example.kafkaconsumerexample.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.kafkaconsumerexample.model.Customer;
import com.example.kafkaconsumerexample.model.Order;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = "test")
	public void consumeMessage(String message) {
		System.out.println("Received message : "+message);
	}
	
	@KafkaListener(topics = "customer", groupId = "group_id", containerFactory = "customerKafkaListenerContainerFactory")
	public void consumerCustomerJson(Customer customer) {
		System.out.println(customer);
	}
	
	@KafkaListener(topics = "order", groupId = "group_id", containerFactory = "orderKafkaListenerContainerFactory")
	public void consumerOrderJson(Order order) {
		System.out.println(order);
	}
}
