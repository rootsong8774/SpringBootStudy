package com.jpaspringcar.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "car_seq_gen", sequenceName = "car_seq", initialValue = 1, allocationSize = 1)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq_gen")
    @Column(name="id")
    private Integer id;
    @Column(name = "model")
    private String model;
    @Column(name = "descr")
    private String descr;
    @Column(name="mpg")
    private Double mpg;
    @Column(name="horsepower")
    private Integer horsePower;
    @Column(name = "year")
    private Integer year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Double getMpg() {
        return mpg;
    }

    public void setMpg(Double mpg) {
        this.mpg = mpg;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
