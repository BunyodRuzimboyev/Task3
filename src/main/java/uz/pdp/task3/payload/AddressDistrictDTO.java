package uz.pdp.task3.payload;

import lombok.Data;

@Data
public class AddressDistrictDTO {
    private Integer districtId;
    private String street;
    private String homeNumber;
}
