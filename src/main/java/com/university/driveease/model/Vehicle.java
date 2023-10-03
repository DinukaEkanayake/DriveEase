package com.university.driveease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Builder //this help to build object in an easy way using design pattern builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "hha")
    private LocalTime time;
    @Column(nullable = false,length = 255)
    private String location;
    @Column(nullable = false,length = 255)
    private String vehicle_no;
    @Column(nullable = false)
    private int mileage;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private String username;



}
