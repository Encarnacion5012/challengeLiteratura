package com.example.challengeLiteratura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ConsumidorAPI_And_Mapper {

    @Autowired
private RestClient restClient;

    public <T> T optenerDatosApi(String url, Class<T> clase){
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(clase);
    }
}
