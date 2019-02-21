package com.ydc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.ydc.excel_to_db.redis.Receiver;
import com.ydc.excel_to_db.util.Constant;

@SpringBootApplication
@ServletComponentScan
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

public class ExcelToDbApplication {
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapterSingle, 
                                            MessageListenerAdapter listenerAdapterList,
                                            
                                            MessageListenerAdapter listenerAdapterIndentSingle,
                                            MessageListenerAdapter listenerAdapterIndentList,
                                            
                                            MessageListenerAdapter listenerAdapterSpecificationSingle,
                                            MessageListenerAdapter listenerAdapterSpecificationList
                                            
    		) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 注入多个消息监听器(receiveSingle/receiveList)
        container.addMessageListener(listenerAdapterSingle, new PatternTopic(Constant.receiveSingle));
        container.addMessageListener(listenerAdapterList, new PatternTopic(Constant.receiveList));
        
        container.addMessageListener(listenerAdapterIndentSingle, new PatternTopic(Constant.receiveIndentSingle));
        container.addMessageListener(listenerAdapterIndentList, new PatternTopic(Constant.receiveIndentList));
        
        container.addMessageListener(listenerAdapterSpecificationSingle, new PatternTopic(Constant.receiveSpecificationSingle));
        container.addMessageListener(listenerAdapterSpecificationList, new PatternTopic(Constant.receiveSpecificationList));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapterSingle(Receiver receiver) {
        return new MessageListenerAdapter(receiver, Constant.singleMethodName);
    }

    @Bean
    MessageListenerAdapter listenerAdapterList(Receiver receiver) {
        return new MessageListenerAdapter(receiver, Constant.listMethodName);
    }
    
    @Bean
    MessageListenerAdapter listenerAdapterIndentSingle(Receiver receiver) {
    	return new MessageListenerAdapter(receiver, Constant.indentMethodName);
    }
    @Bean
    MessageListenerAdapter listenerAdapterIndentList(Receiver receiver) {
    	return new MessageListenerAdapter(receiver, Constant.listMethodIName);
    }
    
    @Bean
    MessageListenerAdapter listenerAdapterSpecificationSingle(Receiver receiver) {
    	return new MessageListenerAdapter(receiver, Constant.specificationMethodName);
    }
    @Bean
    MessageListenerAdapter listenerAdapterSpecificationList(Receiver receiver) {
    	return new MessageListenerAdapter(receiver, Constant.listMethodSName);
    }

    @Bean
    Receiver receiver() {
        return new Receiver();
    }

    public static void main(String[] args) {
        SpringApplication.run(ExcelToDbApplication.class, args);
    }

}
