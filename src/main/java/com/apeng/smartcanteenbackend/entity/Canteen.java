package com.apeng.smartcanteenbackend.entity;

import jakarta.persistence.Column;
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
    private int peopleNum;
    private int capacity;
    @Column(columnDefinition="TEXT")
    private String announcement;
    @Column(columnDefinition="TEXT")
    private String imageUrl;

}
