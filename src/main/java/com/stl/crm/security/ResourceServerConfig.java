package com.stl.crm.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
 
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
 
	/**
	 * O método configure (HttpSecurity http) configura as regras de acesso 
	 * e as correspondentes de solicitação (caminho) para recursos protegidos
	 * usando a classe HttpSecurity.
	 * Nós garantimos os caminhos de URL / api / *, permitindo apenas que os usuários 
	 * autenticados que tenham a função USER ou ADMIN para acessá-lo.
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//-- define URL patterns to enable OAuth2 security
		http.
		anonymous().disable()
		.requestMatchers().antMatchers("/api/**")
		.and().authorizeRequests()
		.antMatchers("/api/**").access("hasRole('ADMIN') or hasRole('USER')")
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
	
}