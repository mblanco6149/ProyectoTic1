package tic1.grupo9.facheritApp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tic1.grupo9.facheritApp.commons.entities.Clothes;
import tic1.grupo9.facheritApp.commons.entities.Local;
import tic1.grupo9.facheritApp.commons.entities.Stock;

import java.util.List;

@Repository
public interface StockRepo extends JpaRepository<Stock,Integer> {
    public List<Stock> findByClothes(Clothes cloth);

    public List<Stock> findByClothesAndAndColorAndAndLocalsAndAndSize(Clothes clothes, String color, Local local,String size);

    @Transactional
    @Modifying
    @Query("update Stock s set s.quantity = :quantity where s.id = :id")
    void updateQuantity(@Param(value = "id") Integer id, @Param(value = "quantity") Integer quantity);


}
