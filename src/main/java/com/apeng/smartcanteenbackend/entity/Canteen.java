package com.apeng.smartcanteenbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Canteen {

    @Id
    private String name;
    private int capacity;
    private int peopleNum;

}
