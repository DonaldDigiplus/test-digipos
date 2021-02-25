package com.devsdg.digipos.GestionStock.Metier;

import com.devsdg.digipos.GestionStock.DTO.StockDTO;
import com.devsdg.digipos.GestionStock.Models.Stock;

public interface StockMetier {
    Stock    saveStock(Stock stock);
    StockDTO addStock(StockDTO stockDTO);
    StockDTO removeStock(StockDTO stockDTO);
    StockDTO reserveStock(StockDTO stockDTO);
    StockDTO retourStock(StockDTO stockDTO);
    boolean checkLimitStock(Long idProduit);
}
