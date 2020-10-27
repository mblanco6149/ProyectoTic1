package tic1.grupo9.facheritApp;


import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import tic1.grupo9.facheritApp.backend.services.*;
import tic1.grupo9.facheritApp.commons.entities.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


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
        Admin admin1 = new Admin("manub@gmail.com", "hola1234");
        as.save(admin1);


        Brand adidas = new Brand("Adidas", "adidas123");
        Local local1 = new Local();

        //Clothes cloth1 = new Clothes("Pantalon slim", "pantalon", 20, "negro", "M", "Masculino",)
        byte[] la = getBytesOfImage(new File(getClass().getClassLoader().getResource("letterIcons/A.png").getFile()));


    }
    @Ignore
    private byte[] getBytesOfImage(File file) throws IOException, URISyntaxException {
        BufferedImage bufferedPic = ImageIO.read(file);
        ByteArrayOutputStream picStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedPic, "jpg", picStream);
        return picStream.toByteArray();
    }

}
