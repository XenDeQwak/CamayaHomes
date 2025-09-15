package com.xen.camaya.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseService<T> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final RestTemplate restTemplate;
    protected final String endpointUrl;
    private final Class<T> modelClass;
    private final Class<T[]> modelArrayClass;

    protected BaseService(String endpointUrl, Class<T> modelClass, Class<T[]> modelArrayClass) {
        this.endpointUrl = endpointUrl;
        this.modelClass = modelClass;
        this.modelArrayClass = modelArrayClass;
        this.restTemplate = initRestTemplate();
    }

    private RestTemplate initRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    public T get(Integer id) {
        String url = endpointUrl + "/" + id;
        HttpEntity<?> request = new HttpEntity<>(null, new HttpHeaders());
        return restTemplate.exchange(url, HttpMethod.GET, request, modelClass).getBody();
    }

    public T[] getAll() {
        HttpEntity<?> request = new HttpEntity<>(null, new HttpHeaders());
        return restTemplate.exchange(endpointUrl, HttpMethod.GET, request, modelArrayClass).getBody();
    }

    public T create(T model) {
        HttpEntity<T> request = new HttpEntity<>(model, new HttpHeaders());
        return restTemplate.exchange(endpointUrl, HttpMethod.PUT, request, modelClass).getBody();
    }

    public T update(T model) {
        logger.info("update: {}", model);
        HttpEntity<T> request = new HttpEntity<>(model, new HttpHeaders());
        return restTemplate.exchange(endpointUrl, HttpMethod.POST, request, modelClass).getBody();
    }

    public void delete(Integer id) {
        logger.info("delete: {}", id);
        String url = endpointUrl + "/" + id;
        HttpEntity<?> request = new HttpEntity<>(null, new HttpHeaders());
        restTemplate.exchange(url, HttpMethod.DELETE, request, Void.class);
    }
}
