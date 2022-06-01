package uz.pdp.task3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String model;

    private String stateNumber;

    @Column(nullable = false)
    private Integer madeYear;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    private Region region;

    @ManyToOne
    private User user;
}
