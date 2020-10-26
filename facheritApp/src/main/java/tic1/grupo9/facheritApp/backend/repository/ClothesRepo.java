package tic1.grupo9.facheritApp.backend.repository;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tic1.grupo9.facheritApp.commons.entities.Clothes;

import java.util.List;


@Repository
public interface ClothesRepo extends JpaRepository<Clothes,Integer> {

    public List<Clothes> findByName(String name);

    public List<Clothes> findByType(String type);


    public List<Clothes> findByColor(String color);


    public List<Clothes> findByPrice(double price);


    public List<Clothes> findBySize(String size);

    public List<Clothes> findByPriceBetween(double price1, double price2);

    public List<Clothes> findByGender(String gender);

    public Clothes findTopByOrderByIdDesc();

    /*@Query("select id from Clothes order by id desc limit 15")
    public List<Clothes> findTopByOrderByIdDesc10();*/
}
