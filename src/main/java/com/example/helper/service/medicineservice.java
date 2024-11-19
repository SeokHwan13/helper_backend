package com.example.helper.service;

import com.example.helper.config.WebClientConfig;
import com.example.helper.entity.medicineAPI;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class medicineservice {

    @Autowired
    private WebClient webClient;

    private final static String key = "BGtEWN1IlvSGC1CZ%2BAwVeqDJdURuUgqhYHbjcRclDEwiqmQurBgqccTKfFaiFQKvBnYEM64oe6tvsfov%2BNK1%2FA%3D%3D";

    public medicineAPI getMedicineList() {
        var response =
                webClient
                        .get()
                        .uri(uriBuilder ->
                                uriBuilder
                                        .path("/DrbEasyDrugInfoService/getDrbEasyDrugList")
                                        .queryParam("serviceKey", key)
                                        .queryParam("pageNo", "3")
                                        .queryParam("numOfRows", "10")
                                        .queryParam("type", "json")
                                        .build())
                        .retrieve()
                        .bodyToMono(medicineAPI.class)
                        .block();

        assert response != null;
        return response;
    }

//
//    public List<medicine> getmedicine_() {
//        return medicinerepository.findAll();
//    }


//    public List<medicine> getColdList(Integer page) {
//        return getMedicineList(page, "감기");
//    }
}
