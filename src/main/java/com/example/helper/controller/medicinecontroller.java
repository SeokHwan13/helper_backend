package com.example.helper.controller;

import com.example.helper.entity.medicineAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class medicinecontroller {
    @Autowired
    private com.example.helper.service.medicineservice medicineservice;


    @GetMapping("/getmedicine")
    public medicineAPI getMedicine(@RequestParam Integer itemSeq) {

        return medicineservice.getMedicine(itemSeq);

    }

    @GetMapping("/getmedicinelist")
    public medicineAPI getBruiseList(@RequestParam Integer eff) throws UnsupportedEncodingException {
        System.out.println(eff);
        return medicineservice.getMedicineList(eff);
    }

    @GetMapping("/getquerymedicine")
    public medicineAPI getQueryMedicine(@RequestParam String query) throws UnsupportedEncodingException {
        System.out.println(query);
        return medicineservice.getQueryMedicine(query);
    }


}
