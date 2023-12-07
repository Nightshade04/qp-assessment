package com.vibhanshu.qpassessment.services;

import com.vibhanshu.qpassessment.entities.GroceryItem;
import com.vibhanshu.qpassessment.entities.ItemOrder;
import com.vibhanshu.qpassessment.models.ItemOrderModel;
import com.vibhanshu.qpassessment.repositories.GroceryRepo;
import com.vibhanshu.qpassessment.repositories.ItemOrderRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class ItemOrderService {

    @Autowired
    private GroceryRepo groceryRepo;

    @Autowired
    private ItemOrderRepo orderRepo;

    public ResponseEntity<ItemOrder> addOrder(@NotNull ItemOrderModel itemOrder) {

        Set<GroceryItem> itemsInOrder = itemOrder.getGroceryItems();
        Set<GroceryItem> orderItemsFromDb = new HashSet<>();
        Map<Integer, Integer> itemIdsInOrder = new HashMap<>();

        itemsInOrder.forEach(item -> {
            GroceryItem itemFromDb = groceryRepo.findById(item.getItemId()).get();
            itemFromDb.setItemQuantity(itemFromDb.getItemQuantity() - item.getItemQuantity());
            itemIdsInOrder.put(item.getItemId(), item.getItemQuantity());
            orderItemsFromDb.add(itemFromDb);
        });

        ItemOrder order = new ItemOrder();
        order.setOrderTotal(itemOrder.getOrderTotal());
        order.setGroceryItems(itemIdsInOrder);

        ItemOrder savedOrder = orderRepo.saveAndFlush(order);

        groceryRepo.saveAllAndFlush(orderItemsFromDb);

        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }
}
