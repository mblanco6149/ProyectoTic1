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

    /*
    @ElementCollection
    @OneToMany
    private Map<Brand, String> myBrandHash;
    */

    public Admin(String email, String password){
        super(email,password);
    }

    /*
    public void addBrand(String name, String passWord){
        Brand newBrand = new Brand(name,passWord);
        if(myBrandHash.get(name)==null){
            myBrandHash.put(newBrand,name);
        }else{
            //exception
        }
    }*/
}
