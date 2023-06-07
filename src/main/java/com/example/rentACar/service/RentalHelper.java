package com.example.rentACar.service;

import com.example.rentACar.entity.Car;
import com.example.rentACar.entity.RentalInfo;

import java.time.LocalDate;
import java.util.List;

public class RentalHelper {
    public static boolean isCarAvailable(Car car, LocalDate startDate, LocalDate endDate) {
        List<RentalInfo> rentalInfos = car.getRentalInfos();

        for (RentalInfo rentalInfo : rentalInfos) {
            LocalDate rentalStartDate = rentalInfo.getStartDate();
            LocalDate rentalEndDate = rentalInfo.getEndDate();

            if (isDateRangeOverlap(rentalStartDate, rentalEndDate, startDate, endDate)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isDateRangeOverlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        // İki tarih aralığı arasında çakışma olup olmadığını kontrol et
        return start1.isBefore(end2) && start2.isBefore(end1);
    }
}
