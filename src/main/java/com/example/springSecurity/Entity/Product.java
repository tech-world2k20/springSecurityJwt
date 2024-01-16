package com.example.springSecurity.Entity;


import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product implements Serializable {

    private int id;
    private String name;
    private int qty;
    private int price;

}
