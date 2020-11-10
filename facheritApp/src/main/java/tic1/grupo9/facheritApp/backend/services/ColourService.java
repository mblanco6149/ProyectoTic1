package tic1.grupo9.facheritApp.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.ClothesRepo;
import tic1.grupo9.facheritApp.backend.repository.ColourRepo;
import tic1.grupo9.facheritApp.commons.entities.Clothes;
import tic1.grupo9.facheritApp.commons.entities.Colour;

import java.util.List;

@Service
public class ColourService {
    @Autowired
    ColourRepo cr;

    public ColourRepo getColourRepo() {
        return cr;
    }

    public void save(Colour colour){
        cr.save(colour);
    }

    public List<Colour> getByColours(String colours){
        return cr.findByColours(colours);
    }

}
