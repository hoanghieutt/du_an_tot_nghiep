package com.poly.sd18.duantotnghiep.repository;

import com.poly.sd18.duantotnghiep.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query(value = "SELECT [id]\n" +
            "      ,[name]\n" +
            "      ,[status]\n" +
            "      ,[description]\n" +
            "      ,[created_date]\n" +
            "      ,[updated_date]\n" +
            "  FROM [dbo].[brands]\n" +
            "WHERE [name] like N'%' + :name + N'%'",nativeQuery = true)
    List<Brand> search(@Param("name") String name);
}
