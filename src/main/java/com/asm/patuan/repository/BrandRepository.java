package com.asm.patuan.repository;


import com.asm.patuan.entity.Brand;
import com.asm.patuan.request.BrandRequest;
import com.asm.patuan.response.BrandReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {


    @Query("SELECT new com.asm.patuan.response.BrandReponse(c.id,c.name) " +
            "FROM Brand c")
    Page<BrandReponse> getAllPage(Pageable pageable);

    @Query("SELECT new com.asm.patuan.response.BrandReponse(c.id,c.name) " +
            "FROM Brand c")
    List<BrandReponse> getAll();

    @Query("SELECT new com.asm.patuan.request.BrandRequest(c.id,c.name) " +
            "FROM Brand c WHERE c.id = ?1")
    BrandRequest findOne(Long id);

}
