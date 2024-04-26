package com.apeng.smartcanteenbackend.entity.sub;


import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Nutrition {

    private double protein;
    private double energy;
    private double fat;

}
