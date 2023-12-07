package com.vibhanshu.qpassessment.repositories;

import com.vibhanshu.qpassessment.entities.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryRepo extends JpaRepository<GroceryItem, Integer> {
}
