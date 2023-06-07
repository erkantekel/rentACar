package com.example.rentACar.service;

import com.example.rentACar.dto.enums.ErrorCode;
import com.example.rentACar.dto.request.RentalRequest;
import com.example.rentACar.dto.response.GetCarsResponse;
import com.example.rentACar.dto.response.RentalResponse;
import com.example.rentACar.entity.Car;
import com.example.rentACar.entity.RentalInfo;
import com.example.rentACar.entity.User;
import com.example.rentACar.exception.GenericException;
import com.example.rentACar.repository.CarRepository;
import com.example.rentACar.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public RentalService(CarRepository carRepository,
                         UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public List<GetCarsResponse> findAvailableCars(LocalDate startDate, LocalDate endDate) {
        List<Car> availableCars = new ArrayList<>();
        List<Car> allCars = carRepository.findAll();

        for (Car car : allCars) {
            if (RentalHelper.isCarAvailable(car, startDate, endDate)) {
                availableCars.add(car);
            }
        }
        List<GetCarsResponse> response = availableCars.stream().map(GetCarsResponse::carToGetCarsResponse).collect(Collectors.toList());
        return response;
    }

    public RentalResponse rent(RentalRequest rentalRequest) {
        final String id = rentalRequest.getCarId();
        final Car car = carRepository.findById(id).orElseThrow(() -> GenericException.builder().errorCode(ErrorCode.CAR_NOT_FOUND).build());
        userRepository.findByUsername(rentalRequest.getUserName())
                .orElseThrow(() -> GenericException.builder().errorCode(ErrorCode.USER_NOT_FOUND).build());
        if (!RentalHelper.isCarAvailable(car, rentalRequest.getStartDate(), rentalRequest.getEndDate())) {
            throw GenericException.builder().errorCode(ErrorCode.BOOKED_DATE).build();
        }
        RentalInfo rentalInfo = new RentalInfo(rentalRequest.getStartDate(),rentalRequest.getEndDate(),rentalRequest.getUserName());
        List<RentalInfo> rentalInfos = car.getRentalInfos();
        rentalInfos.add(rentalInfo);
        car.setRentalInfos(rentalInfos);
        carRepository.save(car);
        return RentalResponse.builder()
                .carId(car.getId())
                .rentalInfos(rentalInfos)
                .build();
    }
}
