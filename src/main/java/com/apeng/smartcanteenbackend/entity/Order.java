package com.apeng.smartcanteenbackend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany()
    private List<Dish> dishes = new ArrayList<>();

    @ManyToOne()
    @JoinColumn
    private User user;

}
