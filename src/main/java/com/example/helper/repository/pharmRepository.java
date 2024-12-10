package com.example.helper.repository;

import com.example.helper.entity.pharm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface pharmRepository extends JpaRepository<pharm, Integer> {

}
