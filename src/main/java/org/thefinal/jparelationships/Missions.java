package org.thefinal.jparelationships;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@Entity(name = "missions")
public class Missions {

    @Id@GeneratedValue
    private Integer Id;
    private String name;
    private int duration;

    @ManyToMany(mappedBy = "missions")
    private List<Employee> employeeList;
}
