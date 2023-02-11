package io.github.oliveira.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class MscloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscloudgatewayApplication.class, args);
	}

	/**
	 * Objeto
	 * que cria a ROTA direto para o discovery server (eureka )
	 * (quando houver uma chamada para localhost:8080/clientes)
	 * 8080 - porta do gateway
	 * /clientes - serviço de clientes que o eureka vai descubrir a porta
	 * atraves do nome
	 * **/
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder
				.routes()
				.route(r -> r.path("/clientes/**").uri("lb://msclientes")) // rota para clientes usando o nome definido no serviço clientes
				.build();
	}
}
