package com.example.helper.controller;

import com.example.helper.entity.medicineAPI;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class medicinecontroller {
    @Autowired
    private com.example.helper.service.medicineservice medicineservice;

//    @GetMapping("/getmedicine")
//    public List<medicine> getMap() {
//        return medicineservice.getmedicine_();
//    }

//    @GetMapping("/getcold")
//    public String getColdList() throws ParseException {
//        String res = medicineservice.getMedicineList();
//        return res;
//    }

    @GetMapping("/getcold")
    public medicineAPI getColdList() {

            // JSON 데이터를 가져옴
            return medicineservice.getMedicineList();

    }
}
