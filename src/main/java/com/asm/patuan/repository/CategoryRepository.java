package com.asm.patuan.repository;

import com.asm.patuan.entity.Category;
import com.asm.patuan.request.CategoryRequest;
import com.asm.patuan.response.CategoryReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new com.asm.patuan.response.CategoryReponse(c.id,c.name) " +
            "FROM Category c")
    Page<CategoryReponse> getAllPage(Pageable pageable);

    @Query("SELECT new com.asm.patuan.response.CategoryReponse(c.id,c.name) " +
            "FROM Category c")
    List<CategoryReponse> getAll();

    @Query("SELECT new com.asm.patuan.request.CategoryRequest(c.id,c.name) " +
            "FROM Category c WHERE c.id = ?1")
    CategoryRequest findOne(Long id);

}
