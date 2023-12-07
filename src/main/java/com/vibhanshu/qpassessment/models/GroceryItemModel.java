package com.vibhanshu.qpassessment.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItemModel {

    private String itemName;
    private double itemPrice;
    private int itemQuantity;

}
