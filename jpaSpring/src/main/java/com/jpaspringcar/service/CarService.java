package com.jpaspringcar.service;

import com.jpaspringcar.domain.Car;
import java.util.List;

public interface CarService {

    public List<Car> list();

    public void register(Car car);

    public Car read(Integer id);

    public void modify(Car car);

    public void delete(Integer id);

}
