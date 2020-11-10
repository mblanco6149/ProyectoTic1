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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;

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
    ColourService cols;

    @Autowired
    SizeService sr;
    
    @Autowired
    ClothesService clothesService;


    @Before
    public void filling() throws IOException, URISyntaxException {
        Admin admin1 = new Admin("manub@gmail.com", "hola1234");
        //as.save(admin1);


        Brand adidas = new Brand("Adidas", "adidas123");
        //bs.save(adidas);
        //Local local1 = new Local();


        byte[] bytePant = getBytesOfImage(new File(("FillingDatabaseImages/adidasPant.jpg")));
        Size m = new Size("M");
        Size s = new Size("S");
        Size l = new Size("L");
        Size xl = new Size("XL");
        java.util.List<Size> myListSize = new LinkedList<Size>();
        myListSize.add(m);myListSize.add(s);myListSize.add(l);myListSize.add(xl);
        Colour color1 = new Colour("Rojo");Colour color2 = new Colour("Azul");Colour color3 = new Colour("Negro");
        java.util.List<Colour> myListColor = new LinkedList<Colour>();
        myListColor.add(color1);myListColor.add(color2);myListColor.add(color3);
        Clothes cloth1 = new Clothes("Pantalon slim", "pantalon", 20, myListColor, myListSize, "Masculino", bytePant, adidas);

        color1.setClothes(cloth1);color2.setClothes(cloth1);color3.setClothes(cloth1);
        m.setClothes_size(cloth1);s.setClothes_size(cloth1);l.setClothes_size(cloth1);xl.setClothes_size(cloth1);
        cloth1.getColor().add(color1);
        cloth1.getColor().add(color2);
        cloth1.getColor().add(color3);

        cloth1.getSize().add(m);
        cloth1.getSize().add(l);
        cloth1.getSize().add(s);
        cloth1.getSize().add(xl);
        clothesService.save(cloth1);
        cols.save(color1);
        cols.save(color2);
        cols.save(color3);

        sr.save(m);
        sr.save(s);
        sr.save(l);
        sr.save(xl);

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
