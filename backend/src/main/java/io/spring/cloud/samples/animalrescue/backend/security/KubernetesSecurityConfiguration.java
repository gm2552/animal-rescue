package io.spring.cloud.samples.animalrescue.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@Profile("k8s")
public class KubernetesSecurityConfiguration {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
		httpSecurity.oauth2ResourceServer(oAuth2ResourceServerSpec -> {
			oAuth2ResourceServerSpec.jwt(jwtSpec -> {
				jwtSpec.jwtAuthenticationConverter(new ReactiveJwtAuthenticationConverterAdapter(new UserNameJwtAuthenticationConverter()));
			});
		});

		return httpSecurity.build();
	}

}
