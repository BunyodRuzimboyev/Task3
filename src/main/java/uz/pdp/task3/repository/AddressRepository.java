package uz.pdp.task3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.task3.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(value = "select  * " +
                   "from address a " +
                   "join district d on a.district_id = district.id " +
                   "join region r on r.id = d.region_id where r.id = : regionId", nativeQuery = true)
    List<Address> getAddressByRegion(Integer regionId);
}
