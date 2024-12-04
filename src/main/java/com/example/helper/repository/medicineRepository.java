package com.example.helper.repository;

import com.example.helper.entity.medicine;
import com.example.helper.entity.pharmacy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface medicineRepository extends JpaRepository<medicine, Integer> {

    @Query("SELECT m FROM medicine m WHERE m.efcyQesitm LIKE CONCAT('%', :keyword, '%')")
    List<medicine> findByEfcyQesitmContainingKeyword(
            @Param("keyword") String keyword,
            Pageable pageable
    );

    @Query("SELECT m FROM medicine m WHERE m.itemName LIKE CONCAT('%', :keyword, '%')")
    List<medicine> findByItemNameContainingKeyword(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
