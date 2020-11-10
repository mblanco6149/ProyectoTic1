package tic1.grupo9.facheritApp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.grupo9.facheritApp.commons.entities.Clothes;
import tic1.grupo9.facheritApp.commons.entities.Stock;

import java.util.List;

@Repository
public interface StockRepo extends JpaRepository<Stock,Integer> {
    public List<Stock> findByClothes(Clothes cloth);

}
