package com.apeng.smartcanteenbackend.entity.sub;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
@Embeddable
@Data
public class Weighting {

    private int count;
    private double weight;

}
