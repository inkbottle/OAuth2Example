package com.yithm.proxy.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Component;

@Component
public class ResDetails {

    @Bean
    @Qualifier(value = "oAuth2ProtectedResourceDetails")
    OAuth2ProtectedResourceDetails getResDetails() {
        return new ClientCredentialsResourceDetails();
    }
}
