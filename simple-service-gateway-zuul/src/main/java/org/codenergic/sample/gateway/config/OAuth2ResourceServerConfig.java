package org.codenergic.sample.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig
        extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(SpringClientFactory springClientFactory) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new RibbonClientHttpRequestFactory(springClientFactory));
        return restTemplate;
    }

    @Primary
    @Bean
    public RemoteTokenServices tokenServices(RestTemplate restTemplate) {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setRestTemplate(restTemplate);
        tokenService.setCheckTokenEndpointUrl("http://AuthorizationServer/oauth/check_token");
        tokenService.setClientId("clientIdPassword");
        tokenService.setClientSecret("secret");
        return tokenService;
    }

}
