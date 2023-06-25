package com.dataintimate.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String edition;
    private int qty;
    private double price;
    private String img;

}
