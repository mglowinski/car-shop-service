package com.xing.gccars.service;

import com.xing.gccars.exception.CarNotFoundException;
import com.xing.gccars.model.Car;
import com.xing.gccars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car getCarById(Long id) throws CarNotFoundException {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found"));
    }

    @Override
    public void deleteCarById(Long id) throws CarNotFoundException {
        Optional<Car> car = carRepository.findById(id);
        if (!car.isPresent()) {
            throw new CarNotFoundException("Car not found");
        }
        carRepository.deleteById(id);
    }

}
