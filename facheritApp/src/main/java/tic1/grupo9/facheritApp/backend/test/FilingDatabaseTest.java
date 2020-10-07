package tic1.grupo9.facheritApp.backend.test;


import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tic1.grupo9.facheritApp.backend.services.AdminService;
import tic1.grupo9.facheritApp.backend.services.BrandService;
import tic1.grupo9.facheritApp.backend.services.ClientService;
import tic1.grupo9.facheritApp.backend.services.LocalService;


//@SpringBootTest
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

    }


}
