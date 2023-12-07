package com.vibhanshu.qpassessment.models;

import com.vibhanshu.qpassessment.entities.GroceryItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrderModel {

    private double orderTotal;
    private Set<GroceryItem> groceryItems;

}
