package com.example.exportfile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class Person {


    private Long id;
    private String name;
    private LocalDate dayOfBirth;
    private String address;
    private String nativeLand;
    private String workUnit;
    private String job;

}
