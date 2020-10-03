package tic1.grupo9.facheritApp.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.ClientRepository;
import tic1.grupo9.facheritApp.commons.entities.Client;
import tic1.grupo9.facheritApp.commons.exceptions.ClientNoExist;

@Service
public class ClientService  {

    @Autowired
    ClientRepository clientRepository;

    public void save(Client client){
        clientRepository.save(client);
    }

    public Client findByEmail(String email) throws ClientNoExist{
        Client client = clientRepository.getOne(email);
        if(client==null){
            throw new ClientNoExist();
        }
        return client;
    }

    public boolean clientExist(String email){
       return clientRepository.existsById(email);
    }

}
