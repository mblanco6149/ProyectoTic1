package tic1.grupo9.facheritApp.backend.repository;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tic1.grupo9.facheritApp.commons.entities.Brand;
import tic1.grupo9.facheritApp.commons.entities.Clothes;

import java.util.List;
import java.util.Set;


@Repository
public interface ClothesRepo extends JpaRepository<Clothes,Integer>, JpaSpecificationExecutor {

    public List<Clothes> findByName(String name);

    public List<Clothes> findByType(String type);

    public List<Clothes> findByGenderAndBrand(String genero, Brand brandName);

    public List<Clothes> findByColor(String color);

    public List<Clothes> findByGenderAndTypeAndBrand(String geenro, String type, Brand brandName);

    public List<Clothes> findByGenderAndTypeAndBrandAndPriceBetween(String genero, String type, Brand brandName, double price1, double price2);


    public List<Clothes> findByPrice(double price);


    public List<Clothes> findBySize(String size);

    public List<Clothes> findByPriceBetween(double price1, double price2);

    public List<Clothes> findByGender(String gender);

    @Query("SELECT c FROM Clothes c WHERE c.gender = ?1 and c.type = ?2")
    public List<Clothes> findAllByGenderAndType(String gender, String type);

    public List<Clothes> findAllByGenderAndTypeAndSize(String gender, String type, String size);

    public List<Clothes> findAllByGenderAndTypeAndSizeAndColor(String gender, String type, String size, String color);

    public List<Clothes> findAllByGenderAndTypeAndSizeAndColorAndPriceBetween(String gender, String type, String size, String color, double price1, double price2);

    public List<Clothes> findAllByGenderAndBrandAndPriceBetween(String gender, Brand brandName, double price1, double price2);

    public List<Clothes> findAllByGenderAndSize(String gender, String size);

    public List<Clothes> findAllByGenderAndSizeAndColor(String gender, String type, String color);

    public List<Clothes> findAllByGenderAndSizeAndPriceBetween(String gender, String size, double price1, double price2);

    public List<Clothes> findAllByGenderAndSizeAndColorAndPriceBetween(String gender, String size, String color, double price1, double price2);

    public List<Clothes> findAllByGenderAndPriceBetween(String gender, double price1, double price2);

    public List<Clothes> findAllByGenderAndTypeAndPriceBetween(String gender, String type, double price1, double price2);

    public Clothes findTopByOrderByIdDesc();

    /*@Query("select id from Clothes order by id desc limit 15")
    public List<Clothes> findTopByOrderByIdDesc10();*/
}
