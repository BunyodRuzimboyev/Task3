package uz.pdp.task3.payload;

import lombok.Data;
@Data
public class CarDTO {
    private String model;
    private String stateNumber;
    private Integer madeYear;
    private String type;
    private Integer regionId;
    private Integer userId;
}
