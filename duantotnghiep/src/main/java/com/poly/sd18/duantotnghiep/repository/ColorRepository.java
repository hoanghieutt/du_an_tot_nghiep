package com.poly.sd18.duantotnghiep.repository;

import com.poly.sd18.duantotnghiep.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    @Query(value = "SELECT [id]\n" +
            "      ,[name]\n" +
            "      ,[status]\n" +
            "      ,[description]\n" +
            "      ,[created_date]\n" +
            "      ,[updated_date]\n" +
            "  FROM [dbo].[colors]\n" +
            "WHERE [name] like N'%' + :name + '%'",nativeQuery = true)
    List<Color> searchColorByName(@Param("name") String name);

    @Query(value = "SELECT [id]\n" +
            "      ,[name]\n" +
            "      ,[status]\n" +
            "      ,[description]\n" +
            "      ,[created_date]\n" +
            "      ,[updated_date]\n" +
            "  FROM [dbo].[colors]\n" +
            "WHERE [name] like N'%' + :name + '%' and [status] = :status",nativeQuery = true)
    List<Color> searchColorByStatus(@Param("name") String name,@Param("status") int status);
}
