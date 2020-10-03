package tic1.grupo9.facheritApp.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.AdminRepo;
import tic1.grupo9.facheritApp.commons.entities.Admin;

@Service
public class AdminService {

    @Autowired
    AdminRepo adminRepo;

    public void save(Admin admin){
        adminRepo.save(admin);
    }


}
