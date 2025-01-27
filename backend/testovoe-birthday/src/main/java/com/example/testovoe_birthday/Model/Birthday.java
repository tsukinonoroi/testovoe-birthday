package com.example.testovoe_birthday.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "birthday")
@Data
public class Birthday implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "birthday_date")
    private Date birthdayDate;
}
