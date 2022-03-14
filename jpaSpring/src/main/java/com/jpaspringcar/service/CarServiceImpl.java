package com.jpaspringcar.service;

import com.jpaspringcar.domain.Car;
import com.jpaspringcar.repository.CarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService{

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> list() {
        return this.carRepository.findAll();
    }

    @Override
    public void register(Car car) {
        this.carRepository.save(car);
    }

    @Override
    public Car read(Integer id) {
        return this.carRepository.getById(id);
    }

    @Override
    public void modify(Car car) {
        Car carEntity = this.carRepository.getById(car.getId());
        carEntity.setModel(car.getModel());
        carEntity.setDescr(car.getDescr());
        carEntity.setMpg(car.getMpg());
        carEntity.setHorsePower(car.getHorsePower());
        carEntity.setYear(car.getYear());
    }

    @Override
    public void delete(Integer id) {
        this.carRepository.deleteById(id);
    }
}
