package com.example.helper.controller;

import com.example.helper.entity.medicine;
import com.example.helper.entity.medicineAPI;
import com.example.helper.entity.medicinee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class medicinecontroller {
    @Autowired
    private com.example.helper.service.medicineservice medicineservice;


//    @GetMapping("/newmed")
//    public void newMed() {
//        medicineservice.newmedicine();
//    }



    @GetMapping("/getmedicine")
    public medicinee getMedicine(@RequestParam Integer itemSeq) {
        return medicineservice.getMedicine(itemSeq);

    }

    @GetMapping("/getmedicinelist")
    public List<medicinee> getBruiseList(@RequestParam Integer efcy) throws IOException {
        System.out.println(efcy);
        return medicineservice.getMedicineList(efcy);
    }

    @GetMapping("/getquerymedicine")
    public List<medicinee> getQueryMedicine(@RequestParam String query) throws UnsupportedEncodingException {
        System.out.println(query);
        return medicineservice.getQueryMedicine(query,0);
    }

}
