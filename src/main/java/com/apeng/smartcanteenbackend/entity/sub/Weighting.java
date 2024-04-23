package com.apeng.smartcanteenbackend.entity.sub;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Weighting {

    @Id
    @GeneratedValue
    private Long id;
    private int count;
    private double weight;

}
