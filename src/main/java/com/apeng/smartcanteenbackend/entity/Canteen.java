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

    /**
     * 食堂人数增加 1
     * @return 更新后的食堂人数
     */
    public int increasePeopleNum() {
        peopleNum++;
        return peopleNum;
    }

    /**
     * 食堂人数减少 1
     * @return 更新后的食堂人数
     */
    public int decreasePeopleNum() {
        if (peopleNum == 0) {
            throw new IllegalStateException("PeopleNum has been ZERO, can not be decreased!");
        }
        peopleNum--;
        return peopleNum;
    }

    /**
     * @return 食堂人数是否为 0
     */
    public boolean isEmpty() {
        return peopleNum == 0;
    }

}
