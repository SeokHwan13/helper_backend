package com.example.helper.controller;

import com.example.helper.entity.pharmacy;
import com.example.helper.service.pharmacyservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class pharmacycontroller {
    @Autowired
    private pharmacyservice pharmacyservice;

    @GetMapping("/getpharmacy")
    public List<pharmacy> getMap() {
        return pharmacyservice.getpharmacy(19280);
    }
}
