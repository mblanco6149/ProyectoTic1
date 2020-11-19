package tic1.grupo9.facheritApp.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.BrandRepo;
import tic1.grupo9.facheritApp.commons.entities.Brand;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    BrandRepo brandRepo;

    public void save(Brand brand){
        brandRepo.save(brand);}

    public List<Brand> getByName (String name){
       return brandRepo.findByName(name);
    }

    public BrandRepo getBrandRepo() {
        return brandRepo;
    }
}
