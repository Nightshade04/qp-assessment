package com.vibhanshu.qpassessment.controllers;

import com.vibhanshu.qpassessment.models.GroceryItemModel;
import com.vibhanshu.qpassessment.models.InventoryModel;
import com.vibhanshu.qpassessment.services.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/grocery/")
public class GroceryController {

    @Autowired
    private GroceryService groceryService;

    @PostMapping("addItem")
    public ResponseEntity<GroceryItemModel> addNewItem(@RequestBody GroceryItemModel itemModel) {
        return groceryService.addNewGroceryItem(itemModel);
    }

    @GetMapping("viewAllItems")
    public ResponseEntity<List<GroceryItemModel>> viewAllItems() {
        return groceryService.viewAllGroceryItems();
    }

    @GetMapping("viewItem/{itemId}")
    public ResponseEntity<GroceryItemModel> viewItem(@PathVariable int itemId) {
        return groceryService.viewGroceryItem(itemId);
    }

    @DeleteMapping("removeItem/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable int itemId) {
        return groceryService.deleteGroceryItem(itemId);
    }

    @PutMapping("updateItem/{itemId}")
    public ResponseEntity<GroceryItemModel> updateItem(@RequestBody GroceryItemModel itemModel, @PathVariable int itemId) {
        return groceryService.updateGroceryItem(itemModel, itemId);
    }

    @PutMapping("manageInventory")
    public ResponseEntity<List<GroceryItemModel>> updateGroceryItemInventory(@RequestBody List<InventoryModel> itemList) {
        return groceryService.updateGroceryItemInventory(itemList);
    }

}
