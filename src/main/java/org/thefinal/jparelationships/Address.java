package org.thefinal.jparelationships;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "address")
public class Address {
    @Id
    @GeneratedValue
    private Integer id;
    private String streetName;
    private String houseNumber;
    private String zipcode;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
