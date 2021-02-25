package com.devsdg.digipos.GestionStock.Services;

import com.devsdg.digipos.GestionStock.DTO.StockDTO;
import com.devsdg.digipos.GestionStock.Metier.StockMetier;
import com.devsdg.digipos.GestionStock.Models.Stock;
import com.devsdg.digipos.GestionStock.Repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockService implements StockMetier {
    final
    StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public StockDTO addStock(StockDTO stockDTO) {
        return null;
    }

    @Override
    public StockDTO removeStock(StockDTO stockDTO) {
        return null;
    }

    @Override
    public StockDTO reserveStock(StockDTO stockDTO) {
        return null;
    }

    @Override
    public StockDTO retourStock(StockDTO stockDTO) {
        return null;
    }

    @Override
    public boolean checkLimitStock(Long idProduit) {
        return false;
    }
}
