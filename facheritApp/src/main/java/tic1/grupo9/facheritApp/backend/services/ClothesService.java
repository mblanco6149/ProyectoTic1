package tic1.grupo9.facheritApp.backend.services;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Clothes getByLastId(){
        return clothesRepo.findTopByOrderByIdDesc();
    }
}
