package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.backend.services.*;
import tic1.grupo9.facheritApp.commons.entities.Clothes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Controller
@EnableAutoConfiguration
public class StartAppiController implements Initializable {

    @Autowired
    AdminService as;

    @Autowired
    ClientService cs;

    @Autowired
    ClothesService cls;

    @Autowired
    LocalService ls;

    @Autowired
    BrandService bs;

    @FXML
    private Menu menuHombre;
    @FXML
    private Menu menuMujer;
    @FXML
    private Menu menuNiños;
    @FXML
    private Menu local;

    @FXML
    private Button carrito;
    @FXML
    private Button login;

    private ObservableList<Clothes> clothes;


    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private TextArea descriptionImage1;
    @FXML
    private VBox hola;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clothes = FXCollections.observableArrayList(cls.getClothesRepo().findAll());

        Clothes cloth = cls.getByLastId();
        image1.setImage(new Image(new ByteArrayInputStream(cls.getByLastId().getClothePicture()), 65, 60, true, true));
        descriptionImage1.setText(cloth.toString());
        hola.getChildren().addAll(cloth.getPicture());
        hola.getChildren().add(descriptionImage1);





    }
}
