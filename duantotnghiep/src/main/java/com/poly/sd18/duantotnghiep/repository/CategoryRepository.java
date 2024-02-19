package com.poly.sd18.duantotnghiep.repository;

import com.poly.sd18.duantotnghiep.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT [id]\n" +
            "      ,[name]\n" +
            "      ,[status]\n" +
            "      ,[description]\n" +
            "      ,[created_date]\n" +
            "      ,[updated_date]\n" +
            "  FROM [dbo].[categories]\n" +
            "WHERE [name] like N'%' + :name + '%'", nativeQuery = true)
    Page<Category> searchAll(Pageable pageable, @Param("name") String name);

    @Query(value = "SELECT [id]\n" +
            "      ,[name]\n" +
            "      ,[status]\n" +
            "      ,[description]\n" +
            "      ,[created_date]\n" +
            "      ,[updated_date]\n" +
            "  FROM [dbo].[categories]\n" +
            "WHERE [name] like N'%' + :name + '%' and [status] = :status", nativeQuery = true)
    Page<Category> searchByStatus(Pageable pageable, @Param("name") String name, @Param("status") int status);
}
