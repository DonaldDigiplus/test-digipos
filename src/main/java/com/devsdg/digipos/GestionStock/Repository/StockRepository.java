package com.devsdg.digipos.GestionStock.Repository;

import com.devsdg.digipos.GestionStock.Models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
