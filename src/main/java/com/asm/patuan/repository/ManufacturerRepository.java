package com.asm.patuan.repository;

import com.asm.patuan.entity.Manufacturer;
import com.asm.patuan.request.ManufacturerRequest;
import com.asm.patuan.response.ManufacturerReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    @Query("SELECT new com.asm.patuan.response.ManufacturerReponse(c.id,c.name) " +
            "FROM Manufacturer c")
    Page<ManufacturerReponse> getAllPage(Pageable pageable);

    @Query("SELECT new com.asm.patuan.response.ManufacturerReponse(c.id,c.name) " +
            "FROM Manufacturer c")
    List<ManufacturerReponse> getAll();

    @Query("SELECT new com.asm.patuan.request.ManufacturerRequest(c.id,c.name) " +
            "FROM Manufacturer c WHERE c.id = ?1")
    ManufacturerRequest findOne(Long id);

}
