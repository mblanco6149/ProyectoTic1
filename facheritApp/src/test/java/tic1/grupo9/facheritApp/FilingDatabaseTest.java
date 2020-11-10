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
import java.util.*;
import java.util.List;

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
        Brand nike = new Brand("Adidas", "adidas123");
        List<Brand> brandList1 = new ArrayList<>();
        brandList1.add(adidas);
        List<Brand> brandList2 = new ArrayList<>();
        brandList2.add(nike);

        Local localAdidas = new Local("Adidas", "adidas567", brandList1);
        Local localNike = new Local("Nike", "Nike1234", brandList2);



        byte[] bytePant = getBytesOfImage(new File(("FillingDatabaseImages/adidasPant.jpg")));
        byte[] byteRemera = getBytesOfImage(new File(("FillingDatabaseImages/orange-tshirt.jpg")));
        byte[] byteRZapato = getBytesOfImage(new File(("FillingDatabaseImages/white-nikes-main.jpg")));
        Size m = new Size("M");
        Size s = new Size("S");
        Size l = new Size("L");
        Size xl = new Size("XL");
        Size mySize38 = new Size("38");
        Size mySize39 = new Size("39");
        Size mySize40 = new Size("40");
        Size mySize41 = new Size("41");
        Size mySize42 = new Size("42");
        Set<Size> mySetSizesZapatos = new HashSet<>(6);mySetSizesZapatos.add(mySize38);mySetSizesZapatos.add(mySize39);mySetSizesZapatos.add(mySize40);mySetSizesZapatos.add(mySize41);mySetSizesZapatos.add(mySize42);
        Set<Size> mySetSizes = new HashSet<>(8);mySetSizes.add(m);mySetSizes.add(s);mySetSizes.add(l);mySetSizes.add(xl);
        //java.util.List<Size> myListSize = new LinkedList<Size>();
        //myListSize.add(m);myListSize.add(s);myListSize.add(l);myListSize.add(xl);
        Colour color1 = new Colour("Rojo");Colour color2 = new Colour("Azul");Colour color3 = new Colour("Negro");Colour naranja = new Colour("Anaranjado");Colour blanco = new Colour("Blanco");
        java.util.List<Colour> myListColor = new LinkedList<Colour>();
        List<Colour> myListOfColours = new ArrayList<>();
        List<Colour> myListOfColoursZapatos = new ArrayList<>();

        myListOfColoursZapatos.add(color3);myListOfColoursZapatos.add(blanco);
        myListOfColours.add(color1);myListOfColours.add(color2);myListOfColours.add(color3);myListOfColours.add(naranja);
        myListColor.add(color1);myListColor.add(color2);myListColor.add(color3);


        Clothes cloth1 = new Clothes("Pantalon slim", "pantalon", 20, myListColor, mySetSizes, "Masculino", bytePant, adidas);
        Clothes cloth2 = new Clothes("Remera deportiva", "Remera", 30, myListOfColours, mySetSizes, "Masculino", byteRemera, nike);
        Clothes cloth3 = new Clothes("air force", "calzado", 30, myListOfColoursZapatos, mySetSizesZapatos, "Masculino", byteRZapato, nike);


        //color1.setClothes(cloth1);color2.setClothes(cloth1);color3.setClothes(cloth1);
        //m.setClothes_size(cloth1);s.setClothes_size(cloth1);l.setClothes_size(cloth1);xl.setClothes_size(cloth1);


        clothesService.save(cloth1);
        clothesService.save(cloth2);
        cols.save(color1);
        cols.save(color2);
        cols.save(color3);
        cols.save(naranja);
        sr.save(m);
        sr.save(s);
        sr.save(l);
        sr.save(xl);

        Stock stock1 = new Stock(cloth1, localAdidas , 5, "M", "Negro");
        Stock stock2 = new Stock(cloth2, localNike, 3, "S", "Anaranjado");
        Stock stock3 = new Stock(cloth3, localNike, 5, "39", "Blanco");
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
