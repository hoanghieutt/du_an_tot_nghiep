package com.poly.sd18.duantotnghiep.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeDTO {
    private String name;

    private Integer status;

    private Integer shirtLength;

    private Integer shirtWidth;
}
