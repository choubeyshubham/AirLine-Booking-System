package in.choubeyshubham.ancillaryservice.mapper;

import in.choubeyshubham.ancillaryservice.model.FlightMeal;

public class FlightMealMapper {

    public static FlightMealResponse toResponse(FlightMeal flightMeal) {
        if (flightMeal == null) return null;

        return FlightMealResponse.builder()
                .id(flightMeal.getId())
                .flightId(flightMeal.getFlightId())
                .meal(MealMapper.toResponse(flightMeal.getMeal()))
                .available(flightMeal.getAvailable())
                .price(flightMeal.getPrice())
                .displayOrder(flightMeal.getDisplayOrder())
                .build();
    }
}
