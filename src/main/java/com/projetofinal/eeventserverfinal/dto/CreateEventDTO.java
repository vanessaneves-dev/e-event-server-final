package com.projetofinal.eeventserverfinal.dto;


import lombok.Data;

@Data
public class CreateEventDTO {

    private String title;
    private String description;
    private String street;
    private String number;
    private String city;
    private String state;
    private String postalCode;
    private String date;
    private String time;
    private String category;
    private String image;
    private String maps;

}
