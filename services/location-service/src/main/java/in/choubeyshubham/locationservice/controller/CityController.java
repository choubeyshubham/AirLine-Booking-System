package in.choubeyshubham.locationservice.controller;

import in.choubeyshubham.locationservice.service.implementation.CityServiceImpl;
import in.choubeyshubham.payload.request.CityRequest;
import in.choubeyshubham.payload.response.ApiResponse;
import in.choubeyshubham.payload.response.CityResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {

    private final CityServiceImpl cityService;

    //------------------   Read / GET -----------------------------
    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityByID(@PathVariable Long id) throws Exception {
        CityResponse cityResponse = cityService.getCityById(id);
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @GetMapping()
    public ResponseEntity<Page<CityResponse>> getAllCities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(cityService.getAllCities(pageable));
    }


    //------------------   Write / POST _----------------------------
    @PostMapping
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest request) throws Exception {
        CityResponse cityResponse = cityService.createCity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cityResponse);
    }


    //--------------- Update / PUT ---------------------------

    @PutMapping("/{id")
    public ResponseEntity<CityResponse> updateCity(@PathVariable Long id, @Valid @RequestBody CityRequest request) throws Exception {
        return ResponseEntity.ok(cityService.updateCity(id, request));
    }
    //--------------- DELETE -------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCity(@PathVariable Long id) throws Exception {
        cityService.deleteCity(id);
        return ResponseEntity.ok(new ApiResponse("City deleted successfully"));
    }

    //-------------------SEARCH  -------------------

    @GetMapping("/search")
    public ResponseEntity<Page<CityResponse>> searchCities(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(cityService.searchCities(keyword, pageable));
    }


    @GetMapping("/country/{countryCode}")
    public ResponseEntity<Page<CityResponse>> searchCitiesByCountry(
            @PathVariable String countryCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(cityService.getCitiesByCountryCode(countryCode.toUpperCase(), pageable));
    }

    @GetMapping("exists/{cityCode}")
    public ResponseEntity<Boolean> checkCityExists(@PathVariable String cityCode){
        return ResponseEntity.ok(cityService.cityExists(cityCode.toUpperCase()));
    }

}
