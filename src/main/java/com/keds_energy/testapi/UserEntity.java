package com.keds_energy.testapi;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "users")
@Data
//@Table(schema = "dbo",name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String name;

    @Column(unique = true, length = 50)
    private String email;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
}
