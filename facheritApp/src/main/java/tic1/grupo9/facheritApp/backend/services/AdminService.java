package tic1.grupo9.facheritApp.backend.services;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.AdminRepo;
import tic1.grupo9.facheritApp.commons.entities.Admin;
import tic1.grupo9.facheritApp.commons.entities.Brand;


@Service
@Data
@NoArgsConstructor
public class AdminService {

    @Autowired
    AdminRepo adminRepo;

    public void save(Admin admin){
        adminRepo.save(admin);
    }

    public AdminRepo getAdminRepo() {
        return adminRepo;
    }


}
