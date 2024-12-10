package com.example.helper.repository;

import com.example.helper.entity.pharmstorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface pharmstorageRepository extends JpaRepository<pharmstorage,Integer > {


    @Query("SELECT p FROM pharmstorage p JOIN FETCH p.pharm WHERE p.medicinee.id = :medicineId")
    List<pharmstorage> findByMedicineIdWithPharm(@Param("medicineId") Integer medicineId);
}
