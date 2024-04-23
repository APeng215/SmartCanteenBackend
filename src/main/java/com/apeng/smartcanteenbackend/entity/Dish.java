package com.apeng.smartcanteenbackend.entity;

import com.apeng.smartcanteenbackend.entity.sub.Nutrition;
import com.apeng.smartcanteenbackend.entity.sub.Weighting;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    private double price;
    private String location;
    private String image_url;

    @OneToOne
    @JoinColumn
    private Nutrition nutrition;

    @OneToOne
    @JoinColumn
    private Weighting weighting;



}
