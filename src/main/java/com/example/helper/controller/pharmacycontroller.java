package com.example.helper.controller;

import com.example.helper.entity.pharmstorage;
import com.example.helper.service.pharmacyservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class pharmacycontroller {
    @Autowired
    private pharmacyservice pharmacyservice;

    @GetMapping("/getpharmacy")
    public ResponseEntity<List<pharmstorage>> getMappharmacy(@RequestParam Integer id) {
        List<pharmstorage> result = pharmacyservice.getpharmacylist(id);
        return ResponseEntity.ok(result);
    }

}
