package com.poly.sd18.duantotnghiep.repository;

import com.poly.sd18.duantotnghiep.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
}
