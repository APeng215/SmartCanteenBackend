package com.apeng.smartcanteenbackend.entity;

import com.apeng.smartcanteenbackend.entity.sub.Nutrition;
import com.apeng.smartcanteenbackend.entity.sub.Weighting;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Dish {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    private double price;
    private String location;
    @Column(length = 65555)
    private String image_url;

    @Embedded
    private Nutrition nutrition = new Nutrition();

    @Embedded
    private Weighting weighting = new Weighting();


}
