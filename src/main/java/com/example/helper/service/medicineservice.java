package com.example.helper.service;

import com.example.helper.config.WebClientConfig;
import com.example.helper.entity.medicine;
import com.example.helper.entity.medicineAPI;
import com.example.helper.repository.medicineRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class medicineservice {

    @Autowired
    private WebClient webClient;

    @Autowired
    private medicineRepository medicineRepository;

    private final static String key = "BGtEWN1IlvSGC1CZ%2BAwVeqDJdURuUgqhYHbjcRclDEwiqmQurBgqccTKfFaiFQKvBnYEM64oe6tvsfov%2BNK1%2FA%3D%3D";
    private final static String[] efcyQesitm = {"감기","타박상","근육통","발열","두통","복통","치통"};


    public List<medicine> getMedicineList(Integer efcy) {

        Pageable limitTen = PageRequest.of(0, 10); // 첫 페이지, 10개 제한
        return medicineRepository.findByEfcyQesitmContainingKeyword(efcyQesitm[efcy], limitTen);
//        String encoded = URLEncoder.encode(efcyQesitm[efcy], "UTF-8");
//        var response =
//                webClient
//                        .get()
//                        .uri(uriBuilder ->
//                                uriBuilder
//                                        .path("/DrbEasyDrugInfoService/getDrbEasyDrugList")
//                                        .queryParam("serviceKey", key)
//                                        .queryParam("efcyQesitm", encoded)
//                                        .queryParam("pageNo", "1")
//                                        .queryParam("numOfRows", "10")
//                                        .queryParam("type", "json")
//                                        .build())
//                        .retrieve()
//                        .bodyToMono(medicineAPI.class)
//                        .block();
//
//        assert response != null;
//        return response;
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

    public List<medicine> getQueryMedicine(String query, Integer page) throws UnsupportedEncodingException {
        Pageable limitTen = PageRequest.of(0, 10); // 첫 페이지, 10개 제한
        String decodedValue = URLDecoder.decode(query, StandardCharsets.UTF_8.name());
        System.out.println("abcde~~~~~: " + decodedValue);
        return medicineRepository.findByItemNameContainingKeyword(decodedValue,limitTen);
    }
//        var response =
//                webClient
//                        .get()
//                        .uri(uriBuilder ->
//                                uriBuilder
//                                        .path("/DrbEasyDrugInfoService/getDrbEasyDrugList")
//                                        .queryParam("serviceKey", key)
//                                        .queryParam("itemName", query)
//                                        .queryParam("pageNo", "1")
//                                        .queryParam("numOfRows", "10")
//                                        .queryParam("type", "json")
//                                        .build())
//                        .retrieve()
//                        .bodyToMono(medicineAPI.class)
//                        .block();
//
//        assert response != null;
//        return response;
//    }

    public void postmedicine(Integer a) {
        var response =
                webClient
                        .get()
                        .uri(uriBuilder ->
                                uriBuilder
                                        .path("/DrbEasyDrugInfoService/getDrbEasyDrugList")
                                        .queryParam("serviceKey", key)
                                        .queryParam("pageNo", a.toString())
                                        .queryParam("numOfRows", "50")
                                        .queryParam("type", "json")
                                        .build())
                        .retrieve()
                        .bodyToMono(medicineAPI.class)
                        .block();

        for(int i = 0; i < 50; i++) {
            medicine med = new medicine();

            assert response != null;
            medicineAPI.Body.Item item = response.getBody().getItems().get(i);
            med.setItemName(item.getItemName());
            med.setEntpName(item.getEntpName());
            med.setEfcyQesitm(response.getBody().getItems().get(i).getEfcyQesitm());
            med.setUseMethod(response.getBody().getItems().get(i).getUseMethodQesitm());
            med.setAtpnQesitm(response.getBody().getItems().get(i).getAtpnQesitm());
            med.setIntrcQesitm(response.getBody().getItems().get(i).getIntrcQesitm());
            med.setSeQesitm(response.getBody().getItems().get(i).getSeQesitm());
            med.setDepositMethod(response.getBody().getItems().get(i).getDepositMethodQesitm());
            med.setItemImage(response.getBody().getItems().get(i).getItemImage());

            medicineRepository.save(med);
        }
    }
}
