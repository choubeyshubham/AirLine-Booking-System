package in.choubeyshubham.pricingservice.mapper;


import in.choubeyshubham.payload.request.FareRulesRequest;
import in.choubeyshubham.payload.response.FareRulesResponse;
import in.choubeyshubham.pricingservice.model.Fare;
import in.choubeyshubham.pricingservice.model.FareRules;

public class FareRuleMapper {

    public static FareRules toEntity(FareRulesRequest request, Fare fare){
        if(request == null)return null;

        return FareRules.builder()
                .ruleName(request.getRuleName())
                .fare(fare)
                .airlineId(request.getAirlineId())
                .isRefundable(request.getIsRefundable())
                .changeFee(request.getChangeFee())
                .cancellationFee(request.getCancellationFee())
                .refundDeadlineDays(request.getRefundDeadlineDays())
                .changeDeadlineHours(request.getChangeDeadlineHours())
                .isChangeable(request.getIsChangeable() != null && request.getIsChangeable())
                .build();

    }

    public static FareRulesResponse toResponse(FareRules fareRules) {
        if (fareRules == null) return null;
        return FareRulesResponse.builder()
                .id(fareRules.getId())
                .ruleName(fareRules.getRuleName())
                .fareId(fareRules.getFare() != null ? fareRules.getFare().getId() : null)
                .airlineId(fareRules.getAirlineId())
                .isRefundable(fareRules.getIsRefundable())
                .changeFee(fareRules.getChangeFee())
                .cancellationFee(fareRules.getCancellationFee())
                .refundDeadlineDays(fareRules.getRefundDeadlineDays())
                .changeDeadlineHours(fareRules.getChangeDeadlineHours())
                .isChangeable(fareRules.getIsChangeable())
                .createdAt(fareRules.getCreatedAt())
                .updatedAt(fareRules.getUpdatedAt())
                .build();
    }

    public static void updateEntity(FareRulesRequest request, FareRules existing) {
        if (request == null || existing == null) return;
        if (request.getRuleName() != null) existing.setRuleName(request.getRuleName());
        if (request.getAirlineId() != null) existing.setAirlineId(request.getAirlineId());
        if (request.getIsRefundable() != null) existing.setIsRefundable(request.getIsRefundable());
        if (request.getChangeFee() != null) existing.setChangeFee(request.getChangeFee());
        if (request.getCancellationFee() != null) existing.setCancellationFee(request.getCancellationFee());
        if (request.getRefundDeadlineDays() != null) existing.setRefundDeadlineDays(request.getRefundDeadlineDays());
        if (request.getChangeDeadlineHours() != null) existing.setChangeDeadlineHours(request.getChangeDeadlineHours());
        if (request.getIsChangeable() != null) existing.setIsChangeable(request.getIsChangeable());
    }
}
