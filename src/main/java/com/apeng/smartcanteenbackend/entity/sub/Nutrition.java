package com.apeng.smartcanteenbackend.entity.sub;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Nutrition {

    @Id
    @GeneratedValue
    private Long id;
    private double protein;
    private double energy;
    private double fat;


}
