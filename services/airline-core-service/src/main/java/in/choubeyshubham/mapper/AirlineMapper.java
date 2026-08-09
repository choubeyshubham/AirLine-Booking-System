package in.choubeyshubham.mapper;

import in.choubeyshubham.embeddable.Support;
import in.choubeyshubham.model.Airline;
import in.choubeyshubham.payload.request.AirlineRequest;
import in.choubeyshubham.payload.response.AirlineResponse;

public class AirlineMapper {

    public static Airline toEntity(AirlineRequest request, Long ownerId) {
        if (request == null) return null;

        Airline airline = Airline.builder()
                .iataCode(request.getIataCode())
                .icaoCode(request.getIcaoCode())
                .name(request.getName())
                .alias(request.getAlias())
                .country(request.getCountry())
                .logoUrl(request.getLogoUrl())
                .website(request.getWebsite())
                .status(request.getStatus())
                .alliance(request.getAlliance())
                .headquartersCityId(request.getHeadquartersCityId())
                .ownerId(ownerId)
                .build();

        if (request.getSupportEmail() != null || request.getSupportPhone() != null
                || request.getSupportHours() != null) {
            airline.setSupport(Support.builder()
                    .email(request.getSupportEmail())
                    .phone(request.getSupportPhone())
                    .hours(request.getSupportHours())
                    .build());
        }

        return airline;
    }

    public static AirlineResponse toResponse(Airline airline) {
        if (airline == null) return null;

        return AirlineResponse.builder()
                .id(airline.getId())
                .iataCode(airline.getIataCode())
                .icaoCode(airline.getIcaoCode())
                .name(airline.getName())
                .alias(airline.getAlias())
                .country(airline.getCountry())
                .logoUrl(airline.getLogoUrl())
                .website(airline.getWebsite())
                .status(airline.getStatus())
                .alliance(airline.getAlliance())
                .support(airline.getSupport())
                .headquartersCityId(airline.getHeadquartersCityId())
//                .supportEmail(airline.getSupport() != null ? airline.getSupport().getEmail() : null)
//                .supportPhone(airline.getSupport() != null ? airline.getSupport().getPhone() : null)
//                .supportHours(airline.getSupport() != null ? airline.getSupport().getHours() : null)
                .support(airline.getSupport())
                .createdAt(airline.getCreatedAt())
                .updatedAt(airline.getUpdatedAt())
                .ownerId(airline.getOwnerId())
                .updatedById(airline.getUpdatedById())
                .build();
    }



    public static void updateEntity(Airline airline, AirlineRequest request) {
        if (airline == null || request == null) return;

        airline.setIataCode(request.getIataCode());
        airline.setIcaoCode(request.getIcaoCode());
        airline.setName(request.getName());
        airline.setAlias(request.getAlias());
        airline.setCountry(request.getCountry());
        airline.setLogoUrl(request.getLogoUrl());
        airline.setWebsite(request.getWebsite());
        airline.setStatus(request.getStatus());
        airline.setAlliance(request.getAlliance());
        airline.setHeadquartersCityId(request.getHeadquartersCityId());

        if (airline.getSupport() == null) {
            airline.setSupport(new Support());
        }
        airline.getSupport().setEmail(request.getSupportEmail());
        airline.getSupport().setPhone(request.getSupportPhone());
        airline.getSupport().setHours(request.getSupportHours());
    }
}
