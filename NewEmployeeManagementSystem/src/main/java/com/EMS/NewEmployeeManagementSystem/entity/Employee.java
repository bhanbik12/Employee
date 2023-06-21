package com.EMS.NewEmployeeManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
 //   @Transient
    @JsonIgnore
    private double salary;

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "address_id")
    private Address address;
}
