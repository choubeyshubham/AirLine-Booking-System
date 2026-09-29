package in.choubeyshubham.pricingservice.service;

import in.choubeyshubham.payload.request.FareRulesRequest;
import in.choubeyshubham.payload.response.FareRulesResponse;

import java.util.List;

public interface FareRulesService {

    FareRulesResponse createFareRules(FareRulesRequest request) throws Exception;
    FareRulesResponse getFareRulesById(Long id) throws Exception;
    FareRulesResponse getFareRulesByFareId(Long fareId);
    List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId);
    FareRulesResponse updateFareRules(Long id, FareRulesRequest request) throws Exception;
    void deleteFareRules(Long id);
}
