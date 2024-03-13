package com.example.movieflowapi.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@Component
public class Actor {
    private int id;
    private String firstName;
    private String lastName;
    private String nationality;
    private String gender;
    private Date birthDate;
}
