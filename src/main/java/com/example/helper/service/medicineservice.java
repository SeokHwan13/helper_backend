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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class medicineservice {

    @Autowired
    private WebClient webClient;

    private final static String key = "BGtEWN1IlvSGC1CZ%2BAwVeqDJdURuUgqhYHbjcRclDEwiqmQurBgqccTKfFaiFQKvBnYEM64oe6tvsfov%2BNK1%2FA%3D%3D";
    private final static String[] efcyQesitm = {"감기","타박상","근육통","발열","두통","복통","치통"};


    public medicineAPI getMedicineList(Integer eff) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(efcyQesitm[eff], "UTF-8");
        var response =
                webClient
                        .get()
                        .uri(uriBuilder ->
                                uriBuilder
                                        .path("/DrbEasyDrugInfoService/getDrbEasyDrugList")
                                        .queryParam("serviceKey", key)
                                        .queryParam("efcyQesitm", encoded)
                                        .queryParam("pageNo", "1")
                                        .queryParam("numOfRows", "10")
                                        .queryParam("type", "json")
                                        .build())
                        .retrieve()
                        .bodyToMono(medicineAPI.class)
                        .block();

        assert response != null;
        return response;
    }

    public medicineAPI getMedicine(Integer itemSeq) {
        var response =
                webClient
                        .get()
                        .uri(uriBuilder ->
                                uriBuilder
                                        .path("/DrbEasyDrugInfoService/getDrbEasyDrugList")
                                        .queryParam("serviceKey", key)
                                        .queryParam("itemSeq", itemSeq)
                                        .queryParam("pageNo", "1")
                                        .queryParam("numOfRows", "10")
                                        .queryParam("type", "json")
                                        .build())
                        .retrieve()
                        .bodyToMono(medicineAPI.class)
                        .block();

        assert response != null;
        return response;
    }

    public medicineAPI getQueryMedicine(String query) {
        var response =
                webClient
                        .get()
                        .uri(uriBuilder ->
                                uriBuilder
                                        .path("/DrbEasyDrugInfoService/getDrbEasyDrugList")
                                        .queryParam("serviceKey", key)
                                        .queryParam("itemName", query)
                                        .queryParam("pageNo", "1")
                                        .queryParam("numOfRows", "10")
                                        .queryParam("type", "json")
                                        .build())
                        .retrieve()
                        .bodyToMono(medicineAPI.class)
                        .block();

        assert response != null;
        return response;
    }
}
