package com.vibhanshu.qpassessment.services;

import com.vibhanshu.qpassessment.entities.GroceryItem;
import com.vibhanshu.qpassessment.models.GroceryItemModel;
import com.vibhanshu.qpassessment.models.InventoryModel;
import com.vibhanshu.qpassessment.repositories.GroceryRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GroceryService {

    @Autowired
    private GroceryRepo groceryRepo;

    public ResponseEntity<GroceryItemModel> addNewGroceryItem(@NotNull GroceryItemModel itemModel) {

        GroceryItem item = new GroceryItem();
        item.setItemName(itemModel.getItemName());
        item.setItemPrice(itemModel.getItemPrice());
        item.setItemQuantity(itemModel.getItemQuantity());

        groceryRepo.save(item);

        return new ResponseEntity<>(itemModel, HttpStatus.OK);

    }

    public ResponseEntity<List<GroceryItemModel>> viewAllGroceryItems() {
        List<GroceryItem> groceryItems = groceryRepo.findAll();
        List<GroceryItemModel> response = new ArrayList<>();

        groceryItems.forEach(item -> {
            GroceryItemModel itemModel = new GroceryItemModel(item.getItemName(), item.getItemPrice(), item.getItemQuantity());
            response.add(itemModel);
        });

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    public ResponseEntity<GroceryItemModel> viewGroceryItem(int itemId) {
        GroceryItem itemFromDb = groceryRepo.findById(itemId).isPresent() ? groceryRepo.findById(itemId).get() : null;
        GroceryItemModel response = new GroceryItemModel(itemFromDb.getItemName(), itemFromDb.getItemPrice(), itemFromDb.getItemQuantity());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteGroceryItem(int itemId) {
        groceryRepo.deleteById(itemId);
        return new ResponseEntity<>("Deleted Item with Id: " + itemId, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<GroceryItemModel> updateGroceryItem(@NotNull GroceryItemModel itemModel, int itemId) {

        GroceryItem itemFromDb = groceryRepo.findById(itemId).isPresent() ? groceryRepo.findById(itemId).get() : null;

        if (itemModel.getItemName() != null) {
            itemFromDb.setItemName(itemModel.getItemName());
        }
        if (itemModel.getItemPrice() > 0.0) {
            itemFromDb.setItemPrice(itemModel.getItemPrice());
        }
        if (itemModel.getItemQuantity() > 0) {
            itemFromDb.setItemQuantity(itemModel.getItemQuantity());
        }

        GroceryItem updatedItem = groceryRepo.save(itemFromDb);

        return new ResponseEntity<>(new GroceryItemModel(updatedItem.getItemName(), updatedItem.getItemPrice(), updatedItem.getItemQuantity()), HttpStatus.OK);
    }

    private InventoryModel findItemById(@NotNull List<InventoryModel> itemList, int id) {
        return itemList.stream().filter(item -> item.getItemId() == id).findFirst().orElseThrow();
    }

    public ResponseEntity<List<GroceryItemModel>> updateGroceryItemInventory(@NotNull List<InventoryModel> itemList) {

        List<Integer> itemIdList = new ArrayList<>();
        List<GroceryItemModel> response = new ArrayList<>();

        itemList.forEach(item -> {
            itemIdList.add(item.getItemId());
        });

        List<GroceryItem> itemListFromDb = groceryRepo.findAllById(itemIdList);

        if(itemListFromDb.size() < itemIdList.size()) {
            throw new NoSuchElementException();
        }

        itemListFromDb.forEach(item -> {
            item.setItemQuantity(
                    findItemById(itemList, item.getItemId()).getItemQuantity()
            );
            response.add(new GroceryItemModel(item.getItemName(), item.getItemPrice(), item.getItemQuantity()));
        });

        groceryRepo.saveAllAndFlush(itemListFromDb);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
