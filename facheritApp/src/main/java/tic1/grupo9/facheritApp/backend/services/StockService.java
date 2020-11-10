package tic1.grupo9.facheritApp.backend.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.ClothesRepo;
import tic1.grupo9.facheritApp.backend.repository.StockRepo;
import tic1.grupo9.facheritApp.commons.entities.Stock;

@Service
@Data
@NoArgsConstructor
public class StockService {
    @Autowired
    StockRepo stockRepo;

    public StockRepo getStockRepo(){
        return stockRepo;
    }

    public void save(Stock stock){
        stockRepo.save(stock);
    }
}
