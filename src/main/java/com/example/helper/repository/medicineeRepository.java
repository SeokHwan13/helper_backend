package com.example.helper.repository;

import com.example.helper.entity.medicine;
import com.example.helper.entity.medicinee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface medicineeRepository extends JpaRepository<medicinee, Integer> {
    @Query("SELECT m FROM medicinee m WHERE m.itemName LIKE CONCAT('%', :keyword, '%')")
    List<medicinee> findByItemNameContainingKeyword(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
