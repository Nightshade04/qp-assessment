package com.vibhanshu.qpassessment.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryModel {

    private int itemId;
    private int itemQuantity;

}
