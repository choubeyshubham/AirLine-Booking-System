package in.choubeyshubham.seatservice.service.impl;


import in.choubeyshubham.enums.CabinClassType;
import in.choubeyshubham.payload.request.CabinClassRequest;
import in.choubeyshubham.payload.response.CabinClassResponse;
import in.choubeyshubham.seatservice.mapper.CabinClassMapper;
import in.choubeyshubham.seatservice.model.CabinClass;
import in.choubeyshubham.seatservice.repository.CabinClassRepository;
import in.choubeyshubham.seatservice.service.CabinClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CabinClassServiceImpl implements CabinClassService {

    private final CabinClassRepository cabinClassRepository;

    @Override
    public CabinClassResponse createCabinClass(CabinClassRequest request) throws Exception {

        if(cabinClassRepository.existsByCodeAndAircraftId(
                request.getCode(),
                request.getAircraftId())
        ){
            throw new Exception("cabin class with code already exist");
        }
        CabinClass cabinClass= CabinClassMapper.toEntity(request);

        CabinClass savedCabin=cabinClassRepository.save(cabinClass);

        return CabinClassMapper.toResponse(savedCabin,null);
    }

    @Override
    public CabinClassResponse getCabinClassById(Long id) throws Exception {
        CabinClass cabinClass=cabinClassRepository.findById(id).orElseThrow(
                ()-> new Exception("cabin class not found with id")
        );
        return CabinClassMapper.toResponse(cabinClass,cabinClass.getSeatMap());
    }

    @Override
    public List<CabinClassResponse> getCabinClassesByAircraftId(Long aircraftId) {
        return cabinClassRepository.findByAircraftId(aircraftId)
                .stream()
                .map(cc-> CabinClassMapper.toResponse(cc, cc.getSeatMap()))
                .collect(Collectors.toList());
    }

    @Override
    public CabinClassResponse getByAircraftIdAndName(Long aircraftId, CabinClassType name) {
        CabinClass cabinClass=cabinClassRepository
                .findByAircraftIdAndName(aircraftId,name);
        return CabinClassMapper.toResponse(cabinClass,null);
    }

    @Override
    public CabinClassResponse updateCabinClass(Long id, CabinClassRequest cabinClassRequest) throws Exception {
        CabinClass cabinClass=cabinClassRepository.findById(id).orElseThrow(
                ()-> new Exception("cabin class not found with id")
        );
//        existing cabin code=> PEC
//        request => BC

//        existing cabin code => BC
//        request => BC, business

        if(cabinClassRepository.existsByCodeAndAircraftIdAndIdNot(
                cabinClassRequest.getCode().toUpperCase(),
                cabinClass.getAircraftId(),
                cabinClass.getId()
        )){
            throw new Exception("cabin class with code already exist");
        }


        CabinClassMapper.updateEntity(cabinClassRequest,cabinClass);
        CabinClass updated=cabinClassRepository.save(cabinClass);
        return CabinClassMapper.toResponse(updated, updated.getSeatMap());
    }

    @Override
    public void deleteCabinClass(Long id) throws Exception {
        CabinClass cabinClass=cabinClassRepository.findById(id).orElseThrow(
                ()-> new Exception("cabin class not found with id")
        );
        cabinClassRepository.delete(cabinClass);
    }
}
