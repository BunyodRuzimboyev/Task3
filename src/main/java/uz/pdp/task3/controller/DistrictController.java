package uz.pdp.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task3.entity.District;
import uz.pdp.task3.entity.Region;
import uz.pdp.task3.payload.DistrictRegionDTO;
import uz.pdp.task3.repository.DistrictRepository;
import uz.pdp.task3.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    RegionRepository regionRepository;

    @GetMapping
    public List<District> getAllDistrict() {
        List<District> allDistrictList = districtRepository.findAll();
        return allDistrictList;
    }

    @GetMapping("/{id}")
    public List<District> getDistrictsByRegionId(@PathVariable("id") Integer regionId) {
        return districtRepository.getDistrictByRegionId(regionId);
    }

    @PostMapping
    public String addDistrict(@RequestBody DistrictRegionDTO districtRegionDTO) {

        boolean exists = districtRepository.existsByNameAndRegionId(districtRegionDTO.getDistrictName(), districtRegionDTO.getRegionId());

        if (exists) return "This region such district exist!";
        District district = new District();
        district.setName(districtRegionDTO.getDistrictName());

        Optional<Region> regionRepositoryById = regionRepository.findById(districtRegionDTO.getRegionId());
        if (!regionRepositoryById.isPresent()) return "Region is not found !";

        districtRepository.save(district);
        return "District is added.";
    }

    @DeleteMapping("/{id}")
    public String deleteDistrict(@PathVariable("/id") Integer districtId) {

        Optional<District> byId = districtRepository.findById(districtId);
        if (byId.isPresent()) {
            districtRepository.deleteById(districtId);
            return "District is deleted.";
        }

        return "District is not found!";

    }

    @PutMapping("/{id}")
    public String editDistrict(@PathVariable("/id") Integer districtId, @RequestBody DistrictRegionDTO districtRegionDTO){

        Optional<District> findByDistrictId = districtRepository.findById(districtId);
        if (!findByDistrictId.isPresent())
            return "This district is not found.";
        boolean exists = regionRepository.existsById(districtRegionDTO.getRegionId());
        if (!exists)
            return "This region is not found.";
        District district = new District();
        district.setName(districtRegionDTO.getDistrictName());
        districtRepository.save(district);
        return "District is added.";

    }
}
