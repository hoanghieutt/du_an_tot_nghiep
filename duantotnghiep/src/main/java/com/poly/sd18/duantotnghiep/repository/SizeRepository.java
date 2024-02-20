package com.poly.sd18.duantotnghiep.repository;

import com.poly.sd18.duantotnghiep.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            "      ,[shirt_length]\n" +
            "      ,[shirt_width]\n" +
            "      ,[created_date]\n" +
            "      ,[updated_date]\n" +
            "  FROM [dbo].[sizes]\n" +
            "WHERE [name] like N'%' + :name + '%'",nativeQuery = true)
    Page<Size> searchSizeByName(Pageable pageable,
                                @Param("name") String name);

    @Query(value = "SELECT [id]\n" +
            "      ,[name]\n" +
            "      ,[status]\n" +
            "      ,[shirt_length]\n" +
            "      ,[shirt_width]\n" +
            "      ,[created_date]\n" +
            "      ,[updated_date]\n" +
            "  FROM [dbo].[sizes]\n" +
            "WHERE [name] like N'%' + :name + '%' and [status] = :status", nativeQuery = true)
    Page<Size> searchSizeByStatus(Pageable pageable,
                              @Param("name") String name,
                              @Param("status") Integer status);

    List<Size> findByName(String name);
}
