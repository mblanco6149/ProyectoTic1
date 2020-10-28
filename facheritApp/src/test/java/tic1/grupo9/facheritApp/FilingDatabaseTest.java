package tic1.grupo9.facheritApp;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import tic1.grupo9.facheritApp.backend.services.*;
import tic1.grupo9.facheritApp.commons.entities.*;
import org.springframework.boot.test.context.SpringBootTest;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
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
    public void filling() throws IOException, URISyntaxException {
        //User user1 = new Admin("manub@gmail.com", "hola1234");
        Admin admin1 = new Admin("manub@gmail.com", "hola1234");
        System.out.println(admin1.getEmail());
        System.out.println(as.getAdminRepo());
        as.save(admin1);


        Brand adidas = new Brand("Adidas", "adidas123");
        bs.save(adidas);
        //Local local1 = new Local();


        byte[] bytePant = getBytesOfImage(new File(("FillingDatabaseImages/adidasPant.jpg")));
        Clothes cloth1 = new Clothes("Pantalon slim", "pantalon", 20, "negro", "M", "Masculino", bytePant, adidas);
        clothesService.save(cloth1);
        System.out.println("jola2");

    }

    @Ignore
    private byte[] getBytesOfImage(File file) throws IOException, URISyntaxException {
        BufferedImage bufferedPic = ImageIO.read(file);
        ByteArrayOutputStream picStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedPic, "jpg", picStream);
        return picStream.toByteArray();
    }

    @Test
    public void load(){

    }

}
