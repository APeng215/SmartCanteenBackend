package com.apeng.smartcanteenbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Canteen {

    @Id
    private String name;
    private int capacity;
    private int peopleNum;

}
