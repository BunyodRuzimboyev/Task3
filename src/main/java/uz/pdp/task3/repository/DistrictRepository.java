package uz.pdp.task3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task3.entity.District;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> getDistrictByRegionId(Integer region_id);

    boolean existsByNameAndRegionId(String name, Integer region_id);
}
