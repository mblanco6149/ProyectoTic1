package tic1.grupo9.facheritApp.backend.services;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.ClothesRepo;
import tic1.grupo9.facheritApp.commons.entities.Clothes;

import java.util.List;

@Service
@Data
public class ClothesService {

    @Autowired
    ClothesRepo clothesRepo;

    public ClothesRepo getClothesRepo() {
        return clothesRepo;
    }

    public void save(Clothes clothes){
        clothesRepo.save(clothes);
    }

    public List<Clothes> getByName(String name){
        return clothesRepo.findByName(name);
    }

    public List<Clothes> getByColor(String color){
        return clothesRepo.findByColor(color);
    }

    public List<Clothes> getByPrice(double price){
        return clothesRepo.findByPrice(price);
    }

    public List<Clothes> getBySize(String size){
        return clothesRepo.findBySize(size);
    }

    public List<Clothes> getByType(String type){
        return clothesRepo.findByType(type);
    }

    public List<Clothes> getByGender(String gender){
        return clothesRepo.findByGender(gender);
    }

    public List<Clothes> getByGenderAndType(String gender, String type){
        return clothesRepo.findAllByGenderAndType(gender, type);
    }

    public List<Clothes> getByGenderAndTypeAndSize(String gender, String type, String size){
        return clothesRepo.findAllByGenderAndTypeAndSize(gender, type, size);
    }

    public List<Clothes> getByGenderAndTypeAndSizeAndColor(String gender, String type, String size, String color){
        return clothesRepo.findAllByGenderAndTypeAndSizeAndColor(gender, type, size, color);
    }

    public List<Clothes> getByGenderAndTypeAndSizeAndColorAndPriceBetween(String gender, String type, String size, String color,double price1, double price2){
        return clothesRepo.findAllByGenderAndTypeAndSizeAndColorAndPriceBetween(gender, type, size, color, price1, price2);
    }

    public List<Clothes> getByGenderAndPriceBetween(String gender, double price1, double price2){
        return clothesRepo.findAllByGenderAndPriceBetween(gender, price1, price2);
    }

    public List<Clothes> getByGenderAndTypeAndPriceBetween(String gender, String type, double price1, double price2){
        return clothesRepo.findAllByGenderAndTypeAndPriceBetween(gender, type, price1, price2);
    }
    public Clothes getByLastId(){
        return clothesRepo.findTopByOrderByIdDesc();
    }

    public List<Clothes> getByArrivals(){return clothesRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));}


}
