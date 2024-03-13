package com.example.movieflowapi.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@Component
public class ProviderCompany {
    private int id;
    private String name;
    private Date foundedDate;
}
