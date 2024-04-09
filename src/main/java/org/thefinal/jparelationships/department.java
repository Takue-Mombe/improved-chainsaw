package org.thefinal.jparelationships;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "department")
@Getter@Setter
public class department {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee>employees;
}
