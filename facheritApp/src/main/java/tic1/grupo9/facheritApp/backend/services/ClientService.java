package tic1.grupo9.facheritApp.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.ClientRepo;
import tic1.grupo9.facheritApp.commons.entities.Client;
import tic1.grupo9.facheritApp.commons.exceptions.ClientNoExist;

@Service
public class ClientService  {

    @Autowired
    ClientRepo clientRepo;

    public void save(Client client){
        clientRepo.save(client);
    }

    public Client findByEmail(String email) throws ClientNoExist{
        Client client = clientRepo.getOne(email);
        if(client==null){
            throw new ClientNoExist();
        }
        return client;
    }

    public boolean clientExist(String email){
       return clientRepo.existsById(email);
    }

}
