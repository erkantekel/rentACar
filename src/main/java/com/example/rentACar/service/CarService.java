package com.example.rentACar.service;

import com.example.rentACar.dto.enums.ErrorCode;
import com.example.rentACar.dto.request.GetCarsRequest;
import com.example.rentACar.dto.request.SaveCarRequest;
import com.example.rentACar.dto.request.UpdateCarRequest;
import com.example.rentACar.dto.response.GetCarsResponse;
import com.example.rentACar.entity.Car;
import com.example.rentACar.exception.GenericException;
import com.example.rentACar.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final MongoTemplate mongoTemplate;

    public GetCarsResponse saveCar(SaveCarRequest request) {
        Car car = SaveCarRequest.saveCarRequestToCar(request);
        return GetCarsResponse.carToGetCarsResponse(carRepository.save(car));
    }

    public Page<GetCarsResponse> getCars(GetCarsRequest request, Pageable pageable) {
        Criteria criteria = getCriteria(request);

        Query query = new Query(criteria);
        long total = mongoTemplate.count(query, Car.class);
        query.with(pageable);
        List<Car> cars = mongoTemplate.find(query, Car.class);
        List<GetCarsResponse> responseList = cars.stream().map(GetCarsResponse::carToGetCarsResponse).collect(Collectors.toList());
        return new PageImpl<>(responseList, pageable, total);

    }

    private Criteria getCriteria(GetCarsRequest request) {
        Criteria criteria = new Criteria();

        if (Objects.nonNull(request.getCarBrand())) {
            String regex = ".*" + request.getCarBrand() + ".*";
            criteria = criteria.and("carBrand").regex(regex, "i");
        }
        if (Objects.nonNull(request.getModel())) {
            String regex = ".*" + request.getModel() + ".*";
            criteria = criteria.and("model").regex(regex, "i");
        }
        if (Objects.nonNull(request.getMaxRentalPrice()) && Objects.nonNull(request.getMinRentalPrice())) {
            criteria = criteria.andOperator(
                    Criteria.where("dailyRentalPrice").lte(request.getMaxRentalPrice()),
                    Criteria.where("dailyRentalPrice").gte(request.getMinRentalPrice())
            );
        } else if (Objects.nonNull(request.getMaxRentalPrice())) {
            criteria = criteria.and("dailyRentalPrice").lte(request.getMaxRentalPrice());
        } else if (Objects.nonNull(request.getMinRentalPrice())) {
            criteria = criteria.and("dailyRentalPrice").gte(request.getMinRentalPrice());
        }
        return criteria;
    }

    public void deleteCar(String carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            // Aracın kiralama bilgilerini kontrol etme veya başka işlemler ekleme

            carRepository.delete(car);
        } else {
            throw GenericException.builder().httpStatus(HttpStatus.NOT_FOUND).errorCode(ErrorCode.CAR_NOT_FOUND).errorMessage("Car not found with id:" + carId).build();
        }
    }

    @Transactional()
    public GetCarsResponse updateCar(UpdateCarRequest updateRequest) {
        final String id = updateRequest.getId();
        final Car car = carRepository.findById(id).orElseThrow(() -> GenericException.builder().errorCode(ErrorCode.CAR_NOT_FOUND).build());

        updateCarFields(updateRequest, car);
        carRepository.save(car);

        return GetCarsResponse.carToGetCarsResponse(car);
    }

    private void updateCarFields(UpdateCarRequest request, Car car) {
        car.setKm(getOrDefault(request.getKm(), car.getKm()));
        car.setDailyRentalPrice(getOrDefault(request.getDailyRentalPrice(), car.getDailyRentalPrice()));
        car.setDescription(getOrDefault(request.getDescription(), car.getDescription()));
        car.setImages(getOrDefault(request.getImages(), car.getImages()));
    }

    private  <T> T getOrDefault(T data, T defaultValue) {
        return Objects.isNull(data) ? defaultValue : data;
    }
}

