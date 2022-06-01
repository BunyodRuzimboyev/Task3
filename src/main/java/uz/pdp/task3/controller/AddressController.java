package uz.pdp.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task3.entity.Address;
import uz.pdp.task3.entity.District;
import uz.pdp.task3.payload.AddressDistrictDTO;
import uz.pdp.task3.repository.AddressRepository;
import uz.pdp.task3.repository.DistrictRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DistrictRepository districtRepository;

    @GetMapping
    public List<Address> getAllAddressList() {
        return addressRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Address> getAddressListByRegion(@PathVariable("/id") Integer regionId) {
        List<Address> addressListByRegion = getAddressListByRegion(regionId);
        return addressListByRegion;
    }

    @PostMapping
    public String addAddress(@RequestBody AddressDistrictDTO addressDistrictDTO) {

        Optional<District> findDistrict = districtRepository.findById(addressDistrictDTO.getDistrictId());
        if (!findDistrict.isPresent())
            return "This district is not exist!";
        Optional<District> byId = districtRepository.findById(addressDistrictDTO.getDistrictId());
        District district = byId.get();
        Address address = new Address();
        address.setDistrict(district);
        address.setStreet(addressDistrictDTO.getStreet());
        address.setHomeNumber(addressDistrictDTO.getHomeNumber());
        addressRepository.save(address);
        return "Address is added.";
    }

    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable("/id") Integer addressId) {

        Optional<Address> byId = addressRepository.findById(addressId);
        if (!byId.isPresent())
            return "This address is not exist!";

        addressRepository.deleteById(addressId);
        return "Address is deleted.";
    }

    @PutMapping("/{id}")
    public String editAddress(@PathVariable("/id") Integer id, @RequestBody AddressDistrictDTO addressDistrictDTO) {

        Optional<District> byId = districtRepository.findById(addressDistrictDTO.getDistrictId());
        District district = byId.get();
        Address address = new Address();
        address.setDistrict(district);
        address.setStreet(addressDistrictDTO.getStreet());
        address.setHomeNumber(addressDistrictDTO.getHomeNumber());
        addressRepository.save(address);
        return "Address is edited.";
    }
}
