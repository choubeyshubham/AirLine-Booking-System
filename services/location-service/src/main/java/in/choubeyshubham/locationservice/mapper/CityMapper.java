package in.choubeyshubham.locationservice.mapper;


import in.choubeyshubham.locationservice.model.City;
import in.choubeyshubham.payload.request.CityRequest;
import in.choubeyshubham.payload.response.CityResponse;

import java.util.List;

public class CityMapper {

    public static City toEntity(CityRequest request) {
        if (request == null) return null;

        return City.builder()
                .name(request.getName())
                .cityCode(request.getCityCode())
                .countryCode(request.getCountryCode())
                .countryName(request.getCountryName())
                .regionCode(request.getRegionCode())
                .build();
    }


    public static CityResponse toResponse(City city) {
        if (city == null) return null;

        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .cityCode(city.getCityCode())
                .countryCode(city.getCountryCode())
                .countryName(city.getCountryName())
                .regionCode(city.getRegionCode())
                .build();
    }

    public static City updateEntity(City city, CityRequest request) {
        if(request.getName() != null) city.setName(request.getName().trim());

        if(request.getCityCode() != null) city.setCityCode(request.getCityCode().toUpperCase().trim());

        if(request.getCountryCode() != null) city.setCountryCode(request.getCountryCode().toUpperCase().trim());

        if (request.getCountryName() != null) city.setCountryName(request.getCountryName().trim());

        if(request.getRegionCode() != null) city.setRegionCode(request.getRegionCode().toUpperCase().trim());

        return city;
    }


}
