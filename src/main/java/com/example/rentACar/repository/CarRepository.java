package com.example.rentACar.repository;

import com.example.rentACar.entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, String> {

}
