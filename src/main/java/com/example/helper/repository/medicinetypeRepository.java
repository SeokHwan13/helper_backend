package com.example.helper.repository;

import com.example.helper.entity.medicine;
import com.example.helper.entity.medicinee;
import com.example.helper.entity.medicinetype;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface medicinetypeRepository extends JpaRepository<medicinetype, Integer> {

    // type이 0인 데이터 가져오기
    List<medicinetype> findByType(Integer type);

    // medicine_id로 Medicine 리스트 가져오기
    @Query("SELECT m FROM medicinee m WHERE m.id IN :medicineIds")
    List<medicinee> findMedicinesByIds(
            @Param("medicineIds") List<Long> medicineIds,
            Pageable pageable
    );
}
