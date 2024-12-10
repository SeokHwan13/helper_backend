package com.example.helper.controller;

import com.example.helper.entity.medicinee;
import com.example.helper.service.medicineservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class medicinecontroller {

    @Autowired
    private medicineservice medicineservice;


//    @GetMapping("/newmed")
//    public void newMed() {
//        medicineservice.newmedicine();
//    }



    @GetMapping("/getmedicine")
    public medicinee getMedicine(@RequestParam Integer itemSeq) throws IOException {
        return medicineservice.getMedicine(itemSeq);

    }

    @GetMapping("/getmedicinelist")
    public List<medicinee> getBruiseList(@RequestParam Integer efcy) throws IOException {
        System.out.println(efcy);
        return medicineservice.getMedicineList(efcy);
    }

    @GetMapping("/getquerymedicine")
    public List<medicinee> getQueryMedicine(@RequestParam String query) throws IOException {
        System.out.println(query);
        return medicineservice.getQueryMedicine(query,0);
    }
//    @GetMapping("/inserta")
//    public void insertstoragea(@RequestParam Integer id, @RequestParam Integer remain, @RequestParam Integer duration) {
//        medicineservice.insertStorageAll(id, remain, duration);
//    }
//    @GetMapping("/insertr")
//    public void insertstoragea(@RequestParam Integer id, @RequestParam Integer remain, @RequestParam Integer duration, @RequestParam Integer num) {
//        medicineservice.insertStorageRandom(id, remain, duration, num);
//    }
}
