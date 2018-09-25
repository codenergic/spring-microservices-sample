/* Copyright (C) 2018 ASYX International B.V. All rights reserved. */
package org.codenergic.sample.gateway.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @author diasa
 * @version 1.0, Sep 25, 2018
 * @since
 */
public class EurekaTokenServices implements ResourceServerTokenServices {
    protected final Log logger = LogFactory.getLog(getClass());
    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();

    private String checkTokenPath;
    private String tokenName = "token";
    private String clientId;
    private String clientSecret;
    private String serviceName;

    private RestOperations restTemplate;
    private DiscoveryClient discoveryClient;

    public EurekaTokenServices() {
        restTemplate = new RestTemplate();
        ((RestTemplate) restTemplate)
                .setErrorHandler(new DefaultResponseErrorHandler() {
                    @Override
                    // Ignore 400
                    public void handleError(ClientHttpResponse response)
                            throws IOException {
                        if (response.getRawStatusCode() != 400) {
                            super.handleError(response);
                        }
                    }
                });
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken)
            throws AuthenticationException, InvalidTokenException {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add(tokenName, accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",
                getAuthorizationHeader(clientId, clientSecret));
        Map<String, Object> map = postForMap(getCheckTokenEndpoint(), formData,
                headers);

        if (map.containsKey("error")) {
            if (logger.isDebugEnabled()) {
                logger.debug("check_token returned error: " + map.get("error"));
            }
            throw new InvalidTokenException(accessToken);
        }
        if (!Boolean.TRUE.equals(map.get("active"))) {
            logger.debug("check_token returned active attribute: "
                    + map.get("active"));
            throw new InvalidTokenException(accessToken);
        }

        return tokenConverter.extractAuthentication(map);
    }

    private String getCheckTokenEndpoint() {
        List<ServiceInstance> serviceInstances = discoveryClient
                .getInstances(getServiceName());
        if (!serviceInstances.isEmpty()) {
            ServiceInstance serviceInstance = serviceInstances.get(0);
            return serviceInstance.getUri() + getCheckTokenPath();
        } else {
            logger.error("no " + getServiceName() + " instance available");
            throw new InvalidTokenException("no service instance available");
        }
    }

    private String getAuthorizationHeader(String clientId,
            String clientSecret) {

        if (clientId == null || clientSecret == null) {
            logger.warn(
                    "Null Client ID or Client Secret detected. Endpoint that requires authentication will reject request with 401 error.");
        }

        String creds = String.format("%s:%s", clientId, clientSecret);
        try {
            return "Basic "
                    + Base64Utils.encodeToString(creds.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Could not convert String");
        }
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        // TODO Auto-generated method stub
        return null;
    }

    private Map<String, Object> postForMap(String path,
            MultiValueMap<String, String> formData, HttpHeaders headers) {
        if (headers.getContentType() == null) {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }
        @SuppressWarnings("rawtypes")
        Map map = restTemplate.exchange(path, HttpMethod.POST,
                new HttpEntity<MultiValueMap<String, String>>(formData,
                        headers),
                Map.class).getBody();
        @SuppressWarnings("unchecked")
        Map<String, Object> result = map;
        return result;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public AccessTokenConverter getTokenConverter() {
        return tokenConverter;
    }

    public void setTokenConverter(AccessTokenConverter tokenConverter) {
        this.tokenConverter = tokenConverter;
    }

    public RestOperations getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DiscoveryClient getDiscoveryClient() {
        return discoveryClient;
    }

    public void setDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public String getCheckTokenPath() {
        return checkTokenPath;
    }

    public void setCheckTokenPath(String checkTokenPath) {
        this.checkTokenPath = checkTokenPath;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
