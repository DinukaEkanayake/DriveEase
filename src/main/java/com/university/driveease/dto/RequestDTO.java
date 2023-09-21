package com.university.driveease.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private Date date;
    private LocalTime time;
    private String location;
    private String vehicle_no;
    private int mileage;
    private String message;


}
