package uz.pdp.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task3.entity.Region;
import uz.pdp.task3.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @GetMapping
    public List<Region> getRegions() {
        List<Region> regionList = regionRepository.findAll();
        return regionList;
    }

    @PostMapping
    public String addRegion(@RequestBody String regionName) {
        boolean existsByName = regionRepository.existsByName(regionName);
        if (existsByName)
            return "This region is already exist!";
        regionRepository.save(new Region(regionName));
        return "Region is added.";

    }

    @DeleteMapping("/{id}")
    public String deleteRegion(@PathVariable Integer id) {
        Optional<Region> optionalFindByInd = regionRepository.findById(id);
        if (optionalFindByInd.isPresent()) {
            regionRepository.deleteById(id);
            return "Region is deleted.";
        }

        return "Region is not exist!";
    }

    @PutMapping("/{id}")
    public String editRegion(@PathVariable Integer id, String regionName) {
        boolean existsById = regionRepository.existsById(id);
        if (!existsById)
            return "Region is not exist!";
        regionRepository.save(new Region(regionName));
        return "Region is edited.";
    }
}
