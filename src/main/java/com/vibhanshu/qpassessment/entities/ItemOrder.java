package com.vibhanshu.qpassessment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private double orderTotal;

    @ElementCollection
    @MapKeyColumn(name="itemIdInOrder")
    @Column(name="itemQuantity")
    @CollectionTable(name="itemsInOrder", joinColumns=@JoinColumn(name="orderId"))
    private Map<Integer, Integer> groceryItems;

}
