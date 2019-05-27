package com.xing.gccars.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvailableCar {

    private Long id;
    private Long carId;
    private String carName;
    private String carDescription;
    private BigDecimal carPrice;
}
