package tic1.grupo9.facheritApp.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.grupo9.facheritApp.backend.repository.UserRepo;
import tic1.grupo9.facheritApp.commons.entities.User;
import tic1.grupo9.facheritApp.commons.exceptions.NoUserFound;
import tic1.grupo9.facheritApp.commons.exceptions.UserAlreadyRegistered;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public void addUser(String emailAddress, String password) throws UserAlreadyRegistered {
        if (userRepo.findOneByEmail(emailAddress) != null){
            throw new UserAlreadyRegistered();
        }
        userRepo.save(new User(emailAddress,password));
    }

    public void save(User user){userRepo.save(user);}

    public User findByEmail(String email) throws NoUserFound {
        User user = userRepo.findOneByEmail(email);
        if(user == null){
            throw new NoUserFound();
        }
        return user;
    }




}
