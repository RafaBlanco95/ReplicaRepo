package com.salesianas.dam.replica.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<InternshipEntity> internships;

    @ManyToOne
    private UserEntity login_user;
}
