package com.example.helper.repository;

import com.example.helper.entity.pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface pharmacyrepository extends JpaRepository<pharmacy, Integer> {

    @Query("SELECT p FROM pharmacy p WHERE p.lat BETWEEN :latMin AND :latMax AND p.lon BETWEEN :lonMin AND :lonMax")
    List<pharmacy> findPharmaciesNearLocation(
            @Param("latMin") double latMin,
            @Param("latMax") double latMax,
            @Param("lonMin") double lonMin,
            @Param("lonMax") double lonMax
    );

}
