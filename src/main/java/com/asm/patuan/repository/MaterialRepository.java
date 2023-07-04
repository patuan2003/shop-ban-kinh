package com.asm.patuan.repository;

import com.asm.patuan.entity.Material;
import com.asm.patuan.request.MaterialRequest;
import com.asm.patuan.response.MaterialReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {


    @Query("SELECT new com.asm.patuan.response.MaterialReponse(c.id,c.name) " +
            "FROM Material c")
    Page<MaterialReponse> getAllPage(Pageable pageable);

    @Query("SELECT new com.asm.patuan.response.MaterialReponse(c.id,c.name) " +
            "FROM Material c")
    List<MaterialReponse> getAll();

    @Query("SELECT new com.asm.patuan.request.MaterialRequest(c.id,c.name) " +
            "FROM Material c WHERE c.id = ?1")
    MaterialRequest findOne(Long id);


}
