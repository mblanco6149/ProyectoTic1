package tic1.grupo9.facheritApp.commons.entities;

import lombok.AllArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Entity
public class Admin extends User {



    public Admin(String email, String password){
        super(email,password);
    }


}
