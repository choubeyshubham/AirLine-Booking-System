package in.choubeyshubham.seatservice.service.impl;


import in.choubeyshubham.enums.SeatAvailabilityStatus;
import in.choubeyshubham.payload.response.SeatInstanceResponse;
import in.choubeyshubham.seatservice.mapper.SeatInstanceMapper;
import in.choubeyshubham.seatservice.model.SeatInstance;
import in.choubeyshubham.seatservice.repository.SeatInstanceRepository;
import in.choubeyshubham.seatservice.service.SeatInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatInstanceImpl implements SeatInstanceService {

    private final SeatInstanceRepository seatInstanceRepository;

    @Override
    public Double calculateSeatPrice(List<Long> seatInstanceIds) {
        List<SeatInstance> seatInstances=seatInstanceRepository.findAllById(seatInstanceIds);

        double price=0;
        for(SeatInstance si:seatInstances){
            double seatPremium=si.getPremiumSupercharge()!=null?
                    si.getPremiumSupercharge():0;
            price+=seatPremium;
        }
        return price;
    }

    @Override
    public SeatInstanceResponse updateSeatInstanceStatus(Long seatInstanceId,
                                                         SeatAvailabilityStatus status) {
        SeatInstance seatInstance=seatInstanceRepository.findById(seatInstanceId).orElse(null);
        if(seatInstance==null){return null;}
        seatInstance.setStatus(status);
        seatInstanceRepository.save(seatInstance);
        return SeatInstanceMapper.toResponse(seatInstance);
    }
}
