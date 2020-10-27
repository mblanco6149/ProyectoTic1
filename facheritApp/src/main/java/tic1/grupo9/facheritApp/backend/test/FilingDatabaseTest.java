package tic1.grupo9.facheritApp.backend.test;


import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import tic1.grupo9.facheritApp.backend.services.*;
import tic1.grupo9.facheritApp.commons.entities.*;


public class FilingDatabaseTest {


    @Autowired
    AdminService as;

    @Autowired
    ClientService cs;

    @Autowired
    BrandService bs;

    @Autowired
    LocalService ls;
    
    @Autowired
    ClothesService clothesService;


    @Before
    public void filling(){
        Admin admin1 = new Admin("manub@gmail.com", "hola1234");
        as.save(admin1);


        Brand adidas = new Brand("Adidas", "adidas123");
        Local local1 = new Local();

        //Clothes cloth1 = new Clothes()



    }


}
