package tic1.grupo9.facheritApp.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.SizeRepo;
import tic1.grupo9.facheritApp.commons.entities.Size;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    SizeRepo sr;

    public SizeRepo getSizeRepo() {
        return sr;
    }

    public void save(Size size){
        sr.save(size);
    }

    public List<Size> getBySizes(String sizes){
        return sr.findBySizes(sizes);
    }
}
