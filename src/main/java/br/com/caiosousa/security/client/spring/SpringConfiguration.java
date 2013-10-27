package br.com.caiosousa.security.client.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import br.com.caiosousa.security.client.Propriedades;

@Configuration
public class SpringConfiguration {

	@Bean
	public RestTemplate restTemplate() throws Exception {
		return new RestTemplate();
	}

	@Bean
	public Propriedades propriedades() throws Exception {
		return new Propriedades();
	}
	
}
