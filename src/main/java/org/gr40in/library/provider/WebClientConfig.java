package org.gr40in.library.provider;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
//    @Bean
//    @LoadBalanced
//    RestClient getRestClient() {
//        return RestClient.create();
//    }

//    @Bean
//    WebClient webClient(LoadBalancerClient lbClient) {
//        return WebClient.builder()
//                .filter(new LoadBalancerExchangeFilterFunction(lbClient))
//                .build();
//    }

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
}
