package tic1.grupo9.facheritApp.backend.test;


import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import tic1.grupo9.facheritApp.backend.services.AdminService;
import tic1.grupo9.facheritApp.backend.services.BrandService;
import tic1.grupo9.facheritApp.backend.services.ClientService;
import tic1.grupo9.facheritApp.backend.services.LocalService;
import tic1.grupo9.facheritApp.commons.entities.Admin;
import tic1.grupo9.facheritApp.commons.entities.Brand;
import tic1.grupo9.facheritApp.commons.entities.Local;
import tic1.grupo9.facheritApp.commons.entities.User;



public class FilingDatabaseTest {


    @Autowired
    AdminService as;

    @Autowired
    ClientService cs;

    @Autowired
    BrandService bs;

    @Autowired
    LocalService ls;
    



    @Before
    public void filling(){
        Admin admin1 = new Admin("manub@gmail.com", "hola1234");
        as.save(admin1);


       /* Brand adidas = new Brand("Adidas",)*/

    }


}
