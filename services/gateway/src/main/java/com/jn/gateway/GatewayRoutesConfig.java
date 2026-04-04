package com.jn.gateway;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        "customer-service",
                        r -> r.path("/api/v1/customers/**").uri("lb://customer-service")
                )
                .route(
                        "order-service",
                        r -> r.path("/api/v1/orders/**").uri("lb://order-service")
                )
                .route(
                        "product-service",
                        r -> r.path("/api/v1/products/**").uri("lb://product-service")
                )
                .route(
                        "payment-service",
                        r -> r.path("/api/v1/payments/**").uri("lb://payment-service")
                )



                .build();


    }
}
