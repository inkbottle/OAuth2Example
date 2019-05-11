package com.yithm.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Shawn Fang
 */
@Configuration
@EnableOAuth2Client
public class OAuthConfiguration {
    @Bean
    protected ClientCredentialsResourceDetails resource() {

        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();

        List scopes = new ArrayList<String>(2);
        resource.setAccessTokenUri("http://localhost:8043/oauth/token");
        resource.setClientId("client");
        resource.setClientSecret("123456");
        resource.setGrantType("client_credentials");
        return resource;
    }

    @Bean
    public OAuth2RestTemplate restTemplate() {
        AccessTokenRequest accessTokenRequest = new DefaultAccessTokenRequest();
        OAuth2RestTemplate template = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(accessTokenRequest));

        ClientCredentialsAccessTokenProvider clientCredentialsAccessTokenProvider = new ClientCredentialsAccessTokenProvider();
        AccessTokenProviderChain provider = new AccessTokenProviderChain(
                Arrays.asList(clientCredentialsAccessTokenProvider));
        template.setAccessTokenProvider(provider);
        return template;
    }
}
