package uz.pdp.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task3.entity.Car;
import uz.pdp.task3.entity.Region;
import uz.pdp.task3.entity.User;
import uz.pdp.task3.payload.CarDTO;
import uz.pdp.task3.repository.CarRepository;
import uz.pdp.task3.repository.RegionRepository;
import uz.pdp.task3.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Page<Car> getAllCars(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);

        // 1 page
        //select * from car limit 10 offset (0*10)
        // 2 page
        //select * from car limit 10 offset (1*10)
        // 3 page
        //select * from car limit 10 offset (2*10)
        // 4 page
        //select * from car limit 10 offset (3*10)

        Page<Car> all = carRepository.findAll(pageable);
        return all;
    }

    @GetMapping("/region/{id}")
    public List<Car> getCarsByRegionId(@PathVariable("/id") Integer regionId) {
        return carRepository.findCarsByRegionId(regionId);
    }

    @GetMapping(value = "/user/{id}")
    public List<Car> getCarsByUserId(@PathVariable Integer id) {
        return getCarsByUserId(id);
    }

    @PostMapping
    public String addCar(@RequestBody CarDTO carDTO) {
        Car car = new Car();
        car.setModel(carDTO.getModel());
        car.setMadeYear(carDTO.getMadeYear());
        car.setType(carDTO.getType());
        car.setStateNumber(carDTO.getStateNumber());
        Optional<Region> byRegionId = regionRepository.findById(carDTO.getRegionId());
        car.setRegion(byRegionId.get());
        Optional<User> byUserId = userRepository.findById(carDTO.getUserId());
        car.setUser(byUserId.get());
        carRepository.save(car);
        return "Car is added.";
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable("/id") Integer carId) {
        Optional<Car> byId = carRepository.findById(carId);
        if (!byId.isPresent())
            return "Car is not found!";
        carRepository.deleteById(carId);
        return "Car is deleted.";
    }

    @PutMapping("/{id}")
    public String editCar(@PathVariable("/id") Integer carId, @RequestBody CarDTO carDTO) {
        Optional<Car> byId = carRepository.findById(carId);
        if (!byId.isPresent())
            return "Car is not found!";
        Car car = byId.get();
        car.setModel(carDTO.getModel());
        car.setMadeYear(carDTO.getMadeYear());
        car.setType(carDTO.getType());
        car.setStateNumber(carDTO.getStateNumber());
        Optional<Region> byRegionId = regionRepository.findById(carDTO.getRegionId());
        car.setRegion(byRegionId.get());
        Optional<User> byUserId = userRepository.findById(carDTO.getUserId());
        car.setUser(byUserId.get());
        carRepository.save(car);
        return "Car is edited.";
    }

}
