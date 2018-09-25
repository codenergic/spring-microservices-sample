package org.codenergic.sample.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig
        extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Primary
    @Bean
    public EurekaTokenServices tokenServices(DiscoveryClient discoveryClient,
            @Value("${authorization.server.name}") String serviceName) {
        final EurekaTokenServices tokenService = new EurekaTokenServices();
        tokenService.setCheckTokenPath("/oauth/check_token");
        tokenService.setDiscoveryClient(discoveryClient);
        tokenService.setServiceName(serviceName);
        tokenService.setClientId("clientIdPassword");
        tokenService.setClientSecret("secret");
        return tokenService;
    }

}
