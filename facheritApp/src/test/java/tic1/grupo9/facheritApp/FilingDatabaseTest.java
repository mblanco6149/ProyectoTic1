package tic1.grupo9.facheritApp;


import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tic1.grupo9.facheritApp.backend.services.AdminService;
import tic1.grupo9.facheritApp.backend.services.ClientService;


@SpringBootTest
public class FilingDatabaseTest {


    @Autowired
    AdminService as;

    @Autowired
    ClientService cs;



    @Before
    public void filling(){

    }


}
