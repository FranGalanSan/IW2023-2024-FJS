package com.example.easycall.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private Date dateBegin;
    private Date dateEnd;
    private double price;

    @ManyToOne
    private Person client;
    /*@OneToOne
    private Person employee; Empleado es una tabala aparte o la misma? */

}
