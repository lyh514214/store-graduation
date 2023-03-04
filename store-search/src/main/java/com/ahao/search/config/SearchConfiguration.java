package com.ahao.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 配置配
 * @Author: ahao
 * @Date: 2023/1/7 16:44
 **/

@Configuration
public class SearchConfiguration {

    /**
     * @Description: mq序列化为json格式
    **/
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * @Description: 连接es库
    **/
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://8.134.159.65:9200")));
        return client;
    }


}
