package tic1.grupo9.facheritApp.commons.entities;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@Entity
public class Admin extends User {

    public Admin(String email, String password){
        super(email,password);
    }
}
