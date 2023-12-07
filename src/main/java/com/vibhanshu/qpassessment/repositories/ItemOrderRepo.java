package com.vibhanshu.qpassessment.repositories;

import com.vibhanshu.qpassessment.entities.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderRepo extends JpaRepository<ItemOrder, Integer> {
}
