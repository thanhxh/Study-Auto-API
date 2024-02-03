package com.thanh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPOJO {
    private String name;
    private int category_id;
    private int price; //2 tỷ mấy
    private String release_date;
    private ArrayList<Integer> image_ids;
    private Boolean status;
}
