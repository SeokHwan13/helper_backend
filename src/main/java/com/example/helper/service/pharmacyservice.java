package com.example.helper.service;

import com.example.helper.entity.pharmacy;
import com.example.helper.entity.pharmstorage;
import com.example.helper.repository.pharmacyrepository;
import com.example.helper.repository.pharmstorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class pharmacyservice {

    @Autowired
    private pharmacyrepository pharmacyrepository;

    @Autowired
    private pharmstorageRepository pharmstorageRepository;

    public List<pharmacy> getpharmacy(Integer id) {
        List<pharmacy> list = new ArrayList<>();

        list.add(pharmacyrepository.findById(id).get());
        list.add(pharmacyrepository.findById(19347).get());

        System.out.println(list);

        return list;
    }

    public List<pharmstorage> getpharmacylist(Integer id) {

        return pharmstorageRepository.findByMedicineIdWithPharm(id);

    }
}
