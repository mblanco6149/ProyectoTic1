package tic1.grupo9.facheritApp.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.AdminRepository;
import tic1.grupo9.facheritApp.commons.entities.Admin;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public void save(Admin admin){
        adminRepository.save(admin);
    }


}
