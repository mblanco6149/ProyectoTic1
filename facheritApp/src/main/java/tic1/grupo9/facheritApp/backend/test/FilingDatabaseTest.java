package tic1.grupo9.facheritApp.backend.test;



import org.junit.Before;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import tic1.grupo9.facheritApp.backend.services.AdminService;
import tic1.grupo9.facheritApp.backend.services.BrandService;
import tic1.grupo9.facheritApp.backend.services.ClientService;
import tic1.grupo9.facheritApp.backend.services.LocalService;


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
