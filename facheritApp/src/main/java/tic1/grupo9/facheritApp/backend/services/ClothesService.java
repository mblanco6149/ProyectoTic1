package tic1.grupo9.facheritApp.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.ClothesRepo;
import tic1.grupo9.facheritApp.commons.entities.Clothes;

@Service
public class ClothesService {

    @Autowired
    ClothesRepo clothesRepo;

    public void save(Clothes clothes){
        clothesRepo.save(clothes);
    }


}
