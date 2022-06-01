package uz.pdp.task3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task3.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    boolean existsByName(String name);
}
