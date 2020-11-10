package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.backend.appi.BackendServiceImp;
import tic1.grupo9.facheritApp.backend.services.*;
import tic1.grupo9.facheritApp.commons.entities.Clothes;
import tic1.grupo9.facheritApp.commons.entities.Colour;
import tic1.grupo9.facheritApp.commons.entities.Size;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

@Controller
@EnableAutoConfiguration
public class BuyProductController implements Initializable {
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

    @Autowired
    BackendServiceImp bsi;

    protected  Clothes clothes;


    @FXML
    Text clothesName;
    @FXML
    ImageView imageView;

    @FXML
    ChoiceBox<String> chooseColour;

    @FXML
    ChoiceBox<String> chooseSize;

    @FXML
    Button buyButton;


    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showClothes();
    }

    public  void  showClothes (){

        Image image = new Image(new ByteArrayInputStream(clothes.getClothPicture()));
        imageView.setImage(image);

        clothesName.setText(clothes.getName());

        List<Colour> colours = clothes.getColor();
        List<String> colourNames = new ArrayList<>();
        for (int i=0; i<colours.size();i++){
            colourNames.add(colours.get(i).getColours());
        }
        ObservableList<String> colourList = FXCollections.observableArrayList(colourNames);
        chooseColour.setItems(colourList);

        Set<Size> sizes = clothes.getSize();
        List<String> sizeNames = new ArrayList<>();
        for (Size s : sizes){
            sizeNames.add(s.getSizes());
        }
        ObservableList<String> sizesList = FXCollections.observableArrayList(sizeNames);
        chooseSize.setItems(sizesList);

    }

    public  void buy(javafx.event.ActionEvent actionEvent){
        buyButton.setText("thanks");
    }
}
