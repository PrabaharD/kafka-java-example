package com.example.kafkaconsumerexample.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.kafkaconsumerexample.model.Customer;
import com.example.kafkaconsumerexample.model.Order;

@EnableKafka
@Configuration
public class KafkaConfiguration {

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		
		Map<String, Object> configs = new HashMap<String, Object>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<String, String>(configs);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
	@Bean
	public ConsumerFactory<String, Customer> jsonConsumerFactory() {
		
		Map<String, Object> configs = new HashMap<String, Object>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<String, Customer>(configs, new StringDeserializer(), new JsonDeserializer<>(Customer.class));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Customer> customerKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Customer> factory = new ConcurrentKafkaListenerContainerFactory<String, Customer>();
		factory.setConsumerFactory(jsonConsumerFactory());
		return factory;
	}
	
	@Bean
	public ConsumerFactory<String, Order> jsonOrderFactory() {
		
		Map<String, Object> configs = new HashMap<String, Object>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<String, Order>(configs, new StringDeserializer(), new JsonDeserializer<>(Order.class));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Order> orderKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Order> factory = new ConcurrentKafkaListenerContainerFactory<String, Order>();
		factory.setConsumerFactory(jsonOrderFactory());
		return factory;
	}
}