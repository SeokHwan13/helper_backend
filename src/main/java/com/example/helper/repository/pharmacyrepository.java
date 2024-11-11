package com.example.helper.repository;

import com.example.helper.entity.pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface pharmacyrepository extends JpaRepository<pharmacy, Integer> {
}
