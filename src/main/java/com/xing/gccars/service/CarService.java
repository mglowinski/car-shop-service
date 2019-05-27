package com.xing.gccars.service;

import com.xing.gccars.exception.CarNotFoundException;
import com.xing.gccars.model.Car;

import java.util.List;

public interface CarService {

    List<Car> getCars();

    Car saveCar(Car car);

    Car getCarById(Long id) throws CarNotFoundException;

    void deleteCarById(Long id) throws CarNotFoundException;
}
