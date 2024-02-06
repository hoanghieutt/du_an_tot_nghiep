package com.poly.sd18.duantotnghiep.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "sizes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int status;

    int shirtLength;

    int shirtWidth;

    int sleeveLength;

    int shoulderLength;

    private String description;

    private LocalDate createdDate;

    private LocalDate updatedDate;
}
