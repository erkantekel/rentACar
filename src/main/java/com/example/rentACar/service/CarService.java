package com.example.rentACar.service;

import com.example.rentACar.dto.request.SaveCarRequest;
import com.example.rentACar.dto.response.SaveCarResponse;
import com.example.rentACar.entity.Car;
import com.example.rentACar.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public SaveCarResponse saveCar (SaveCarRequest request){
        Car car = SaveCarRequest.saveCarRequestToCar(request);
        return SaveCarResponse.carToSaveCarResponse(carRepository.save(car));
    }

}
