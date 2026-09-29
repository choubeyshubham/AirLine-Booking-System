package in.choubeyshubham.pricingservice.service.impl;


import in.choubeyshubham.payload.request.FareRulesRequest;
import in.choubeyshubham.payload.response.FareRulesResponse;
import in.choubeyshubham.pricingservice.mapper.FareRuleMapper;
import in.choubeyshubham.pricingservice.model.Fare;
import in.choubeyshubham.pricingservice.model.FareRules;
import in.choubeyshubham.pricingservice.repository.FareRepository;
import in.choubeyshubham.pricingservice.repository.FareRuleRepository;
import in.choubeyshubham.pricingservice.service.FareRulesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FareRulesServiceImpl implements FareRulesService {

    private final FareRepository fareRepository;
    private final FareRuleRepository fareRuleRepository;

    @Override
    public FareRulesResponse createFareRules(FareRulesRequest request) throws Exception {
        Fare fare=fareRepository.findById(request.getFareId())
                .orElseThrow(()-> new Exception("Fare not found"));

        if(fareRuleRepository.existsByFareId(fare.getId())){
            throw new Exception("Fare already exists");
        }

        FareRules fareRules= FareRuleMapper.toEntity(request,fare);
        FareRules savedFareRules= fareRuleRepository.save(fareRules);

        return FareRuleMapper.toResponse(savedFareRules);
    }

    @Override
    public FareRulesResponse getFareRulesById(Long id) throws Exception {
        FareRules fareRules= fareRuleRepository.findById(id).orElseThrow(
                ()-> new Exception("fare rule not found")
        );
        return FareRuleMapper.toResponse(fareRules);
    }

    @Override
    public FareRulesResponse getFareRulesByFareId(Long fareId) {
        FareRules fareRules= fareRuleRepository.findByFareId(fareId);

        return FareRuleMapper.toResponse(fareRules);
    }

    @Override
    public List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId) {
        return fareRuleRepository.findByAirlineId(airlineId).stream()
                .map(FareRuleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FareRulesResponse updateFareRules(Long id, FareRulesRequest request) throws Exception {

        FareRules fareRules= fareRuleRepository.findById(id).orElseThrow(
                ()-> new Exception("fare rule not found")
        );
        FareRuleMapper.updateEntity(request,fareRules);
        FareRules savedFareRules= fareRuleRepository.save(fareRules);
        return FareRuleMapper.toResponse(savedFareRules);
    }

    @Override
    public void deleteFareRules(Long id) {
        FareRules fareRules = fareRuleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Fare rules not found with id: " + id));
        fareRuleRepository.delete(fareRules);
    }
}
