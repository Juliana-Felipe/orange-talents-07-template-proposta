package br.com.zupacademy.proposta.proposta.config.segurança;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@EnableWebSecurity
@Configuration

public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests .antMatchers(HttpMethod.GET, "/topicos").permitAll()
                        .antMatchers(HttpMethod.GET, "/detalhamento*").hasAuthority("SCOPE_escopo-api-proposta-juliana")
                        .antMatchers(HttpMethod.POST, "/propostas/*").hasAuthority("SCOPE_escopo-api-proposta-juliana")
                        .antMatchers(HttpMethod.POST, "/biometria/**").hasAuthority("SCOPE_escopo-api-proposta-juliana")
                ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

    }

}
