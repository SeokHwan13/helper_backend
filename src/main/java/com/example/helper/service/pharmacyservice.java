package com.example.helper.service;

import com.example.helper.entity.pharmacy;
import com.example.helper.repository.pharmacyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class pharmacyservice {

    @Autowired
    private pharmacyrepository pharmacyrepository;

    public pharmacy getpharmacy(Integer id) {
        return pharmacyrepository.findById(id).get();
    }
}
