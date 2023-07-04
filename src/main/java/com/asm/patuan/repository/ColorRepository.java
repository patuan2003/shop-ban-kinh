package com.asm.patuan.repository;

import com.asm.patuan.entity.Color;
import com.asm.patuan.request.ColorRequest;
import com.asm.patuan.response.ColorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query("SELECT new com.asm.patuan.response.ColorResponse(c.id,c.name) " +
            "FROM Color c")
    Page<ColorResponse> getAllPage(Pageable pageable);

    @Query("SELECT new com.asm.patuan.response.ColorResponse(c.id,c.name) " +
            "FROM Color c")
    List<ColorResponse> getAll();

    @Query("SELECT new com.asm.patuan.request.ColorRequest(c.id,c.name) " +
            "FROM Color c WHERE c.id = ?1")
    ColorRequest findOne(Long id);

}
