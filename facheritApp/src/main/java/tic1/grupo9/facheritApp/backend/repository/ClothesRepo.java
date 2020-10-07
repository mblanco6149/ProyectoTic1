package tic1.grupo9.facheritApp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tic1.grupo9.facheritApp.commons.entities.Clothes;

import java.util.List;

@Repository
public interface ClothesRepo extends JpaRepository<Clothes,Integer> {

    @Query("SELECT c.id FROM Clothes c where c.name = :name")
    public List<Clothes> findByName(String name);

    @Query("SELECT c.id FROM Clothes c where c.type = :type")
    public List<Clothes> findByType(String type);

    @Query("SELECT c.id FROM Clothes c where c.color = :color")
    public List<Clothes> findByColor(String color);

    @Query("SELECT c.id FROM Clothes c where c.price = :price")
    public List<Clothes> findByPrice(double price);

    @Query("SELECT c.id FROM Clothes c where c.size = :size")
    public List<Clothes> findBySize(String size);

    public List<Clothes> findByPriceBetween(double price1, double price2);


}
