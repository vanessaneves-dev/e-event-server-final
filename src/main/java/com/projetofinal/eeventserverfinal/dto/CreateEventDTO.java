package com.projetofinal.eeventserverfinal.dto;

import com.projetofinal.eeventserverfinal.models.Address;
import lombok.Data;

@Data
public class CreateEventDTO {

    private String title;
    private String description;
    private Address location;
    private String date;
    private String time;
    private String category;
    private String image;

}
