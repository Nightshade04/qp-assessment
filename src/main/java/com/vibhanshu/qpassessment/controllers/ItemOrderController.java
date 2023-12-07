package com.vibhanshu.qpassessment.controllers;

import com.vibhanshu.qpassessment.entities.ItemOrder;
import com.vibhanshu.qpassessment.models.ItemOrderModel;
import com.vibhanshu.qpassessment.services.ItemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders/")
public class ItemOrderController {

    @Autowired
    private ItemOrderService orderService;

    @PostMapping("addOrder")
    public ResponseEntity<ItemOrder> addItemOrder(@RequestBody ItemOrderModel itemOrder) {
        return orderService.addOrder(itemOrder);
    }

}
