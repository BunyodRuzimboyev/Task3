package uz.pdp.task3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.task3.entity.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findCarsByRegionId(Integer region_id);

    @Query(value = "Select * from car c join users u on u.id = car.user_id where user.id = :user_id ", nativeQuery = true)
    List<Car>  getCarsByUserId(Integer user_id);
}
