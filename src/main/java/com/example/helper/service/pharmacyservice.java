package com.example.helper.service;

import com.example.helper.entity.pharmacy;
import com.example.helper.repository.pharmacyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class pharmacyservice {

    @Autowired
    private pharmacyrepository pharmacyrepository;

    public List<pharmacy> getpharmacy(Integer id) {
        List<pharmacy> list = List.of();

        list.add(pharmacyrepository.findById(id).get());
        list.add(pharmacyrepository.findById(19347).get());

        return list;
    }
}
