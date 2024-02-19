package com.poly.sd18.duantotnghiep.repository;

import com.poly.sd18.duantotnghiep.entity.Color;
import com.poly.sd18.duantotnghiep.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    @Query(value = "SELECT [id]\n" +
            "      ,[name]\n" +
            "      ,[status]\n" +
            "      ,[description]\n" +
            "      ,[created_date]\n" +
            "      ,[updated_date]\n" +
            "  FROM [dbo].[sizes]\n" +
            "WHERE [name] like N'%' + :name + '%'",nativeQuery = true)
    List<Size> searchSizeByName(@Param("name") String name);
}
