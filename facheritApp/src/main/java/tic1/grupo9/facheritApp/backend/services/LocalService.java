package tic1.grupo9.facheritApp.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.LocalRepo;
import tic1.grupo9.facheritApp.commons.entities.Local;

@Service
public class LocalService  {

    @Autowired
    LocalRepo localRepo;

    public void save(Local local){
        localRepo.save(local);
    }

    public LocalRepo getLocalRepo() {
        return localRepo;
    }
}
