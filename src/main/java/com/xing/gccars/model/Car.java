package com.xing.gccars.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "CARS")
@NoArgsConstructor
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    private String description;
    @DecimalMin(value = "0.1")
    private BigDecimal totalPrice;
    @DecimalMin(value = "0.1")
    private BigDecimal rentalPricePerDay;

    @ManyToMany(mappedBy = "cars")
    private List<User> users;
    @OneToMany(mappedBy = "car")
    private List<BorrowedDate> borrowedDates;
}
