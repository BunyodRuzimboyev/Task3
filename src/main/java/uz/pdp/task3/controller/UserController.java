package uz.pdp.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task3.entity.Address;
import uz.pdp.task3.entity.District;
import uz.pdp.task3.entity.User;
import uz.pdp.task3.payload.UserAddressDTO;
import uz.pdp.task3.repository.AddressRepository;
import uz.pdp.task3.repository.DistrictRepository;
import uz.pdp.task3.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    AddressRepository addressRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public String addUser(@RequestBody UserAddressDTO userAddressDTO) {
        Address address = new Address();
        Optional<District> byId = districtRepository.findById(userAddressDTO.getDistrictId());
        District district = byId.get();
        address.setDistrict(district);
        address.setStreet(userAddressDTO.getStreet());
        address.setHomeNumber(userAddressDTO.getHomeNumber());
        Address savedAddress = addressRepository.save(address);
        User user = new User();
        user.setName(userAddressDTO.getUserName());
        user.setAddress(savedAddress);
        userRepository.save(user);
        return "User is added.";

    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("/id") Integer userId) {

        Optional<User> byId = userRepository.findById(userId);
        if (!byId.isPresent())
            return "User is not found!";
        userRepository.deleteById(userId);
        return "User is deleted.";
    }

    @PutMapping("/{id}")
    public String editUser(@PathVariable("/id") Integer userId, @RequestBody UserAddressDTO userAddressDTO) {

        Optional<User> byUserId = userRepository.findById(userId);
        if (!byUserId.isPresent())
            return "User is not found!";

        Address address = new Address();
        Optional<District> byId = districtRepository.findById(userAddressDTO.getDistrictId());
        District district = byId.get();
        address.setDistrict(district);
        address.setStreet(userAddressDTO.getStreet());
        address.setHomeNumber(userAddressDTO.getHomeNumber());
        Address savedAddress = addressRepository.save(address);
        User user = new User();
        user.setName(userAddressDTO.getUserName());
        user.setAddress(savedAddress);
        userRepository.save(user);
        return "User is edited";
    }
}
