package com.jpaspringcar.repository;

import com.jpaspringcar.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {

}
