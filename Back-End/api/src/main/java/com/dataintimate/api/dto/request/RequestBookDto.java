package com.dataintimate.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestBookDto {
    private String id;
    private String title;
    private String author;
    private String edition;
    private int qty;
    private double price;
    private String img;
}
