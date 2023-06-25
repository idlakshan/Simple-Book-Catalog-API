package com.dataintimate.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
    private String id;
    private String title;
    private String author;
    private String edition;
    private int qty;
    private double price;
    private String img;
}
