package com.salesianas.dam.replica.persistence.entity;




import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;


@Entity
@Table(name = "login_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
