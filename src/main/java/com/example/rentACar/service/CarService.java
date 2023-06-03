package com.example.rentACar.service;

import com.example.rentACar.dto.enums.CarBrand;
import com.example.rentACar.dto.request.GetCarsRequest;
import com.example.rentACar.dto.request.SaveCarRequest;
import com.example.rentACar.dto.response.GetCarsResponse;
import com.example.rentACar.dto.response.SaveCarResponse;
import com.example.rentACar.entity.Car;
import com.example.rentACar.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final MongoTemplate mongoTemplate;

    public SaveCarResponse saveCar(SaveCarRequest request) {
        Car car = SaveCarRequest.saveCarRequestToCar(request);
        return SaveCarResponse.carToSaveCarResponse(carRepository.save(car));
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
                    Criteria.where("dailyRentalPrice").lt(request.getMaxRentalPrice()),
                    Criteria.where("dailyRentalPrice").gt(request.getMinRentalPrice())
            );
        } else if (Objects.nonNull(request.getMaxRentalPrice())) {
            criteria = criteria.and("dailyRentalPrice").lt(request.getMaxRentalPrice());
        } else if (Objects.nonNull(request.getMinRentalPrice())) {
            criteria = criteria.and("dailyRentalPrice").gt(request.getMinRentalPrice());
        }
        return criteria;
    }

}
