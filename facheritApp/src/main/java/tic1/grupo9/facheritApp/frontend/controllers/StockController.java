package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.FacheritAppApplication;
import tic1.grupo9.facheritApp.backend.services.BrandService;
import tic1.grupo9.facheritApp.backend.services.ClothesService;
import tic1.grupo9.facheritApp.backend.services.StockService;
import tic1.grupo9.facheritApp.commons.entities.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;



import  javafx.scene.control.Button;

@Controller
@EnableAutoConfiguration
public class StockController implements Initializable {


    @FXML
    Button addButton;
    @FXML
    ChoiceBox<String>  pickBrand;
    @FXML
    ChoiceBox<String>  pickName;
    @FXML
    ChoiceBox<String>  pickColor;
    @FXML
    ChoiceBox<String>  pickSize;
    @FXML
    ChoiceBox<String>  pickLocal;

    @FXML
    Spinner<Integer> spinner;

    @FXML
    Text succesText;

    @Autowired
    StockService ss;
    @Autowired
    StartAppiController startAppiController;
    @Autowired
    AdminController adminController;


    private List<Clothes> clothesList;
    private List<Brand> brandList;
    private List<Local> localList;

    private Brand brand;
    private Clothes cloth;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(clothesList==null){
            clothesList =startAppiController.getClothesToShow();
        }

        if(brandList==null){
            brandList = adminController.getBrandList();
        }
        if(localList==null){
            localList = adminController.getLocalList();
        }

        showSelection();
    }

    public void showSelection(){

        List<String> brands = new ArrayList<>();
        for (Brand b:brandList) {
            brands.add(b.getName());
        }

        ObservableList<String> brandObservableList = FXCollections.observableArrayList(brands);
        pickBrand.setItems(brandObservableList);

        pickBrand.getSelectionModel().selectedItemProperty().addListener(
                (v,brandold,brand1) -> {
                    for (Brand b : brandList) {
                        if (b.getName().equals(brand1)) {
                            brand = b;
                        }
                    }

                    List<String> locals = new ArrayList<>();
                    for (Local l: brand.getLocales()) {
                        locals.add(l.getName());

                    }
                    ObservableList<String> localObservableList = FXCollections.observableArrayList(locals);
                    pickLocal.setItems(localObservableList);

                    List<String> clothes = new ArrayList<>();
                    for (Clothes c: clothesList) {
                        if(c.getBrand().equals(brand)){
                            clothes.add(c.getName());
                        }
                    }
                    ObservableList<String> clothesObservableList = FXCollections.observableArrayList(clothes);
                    pickName.setItems(clothesObservableList);

                    pickName.getSelectionModel().selectedItemProperty().addListener(
                            (u,oldname,newname)->{
                                for(Clothes c : clothesList){
                                    if(c.getName().equals(newname)){
                                        cloth=c;
                                    }
                                }

                                List<String> colours = new ArrayList<>();
                                List<Colour> colourList = cloth.getColor();
                                for (Colour c:colourList) {
                                    colours.add(c.getColours());
                                }
                                ObservableList<String> colourObservableList = FXCollections.observableArrayList(colours);
                                pickColor.setItems(colourObservableList);

                                Set<Size> sizes = cloth.getSize();
                                List<String> sizeNames = new ArrayList<>();
                                for (Size s : sizes){
                                    sizeNames.add(s.getSizes());
                                }
                                ObservableList<String> sizesList = FXCollections.observableArrayList(sizeNames);
                                pickSize.setItems(sizesList);
                            }
                    );



                }
        );


    }

    @FXML
    public void add(javafx.event.ActionEvent actionEvent){


        String color = pickColor.getSelectionModel().getSelectedItem();
        String size = pickSize.getSelectionModel().getSelectedItem();
        Brand brand = null;
        for (Brand b:brandList) {
            if(pickBrand.getSelectionModel().getSelectedItem().equals(b.getName())){
                brand = b;
            }
        }
        Clothes clothes = null;
        for (Clothes c:clothesList) {
            if(pickName.getSelectionModel().getSelectedItem().equals(c.getName())){
                clothes = c;
            }
        }
        Local local = null;
        for (Local l:localList) {
            if(pickLocal.getSelectionModel().getSelectedItem().equals(l.getName())){
                local=l;
            }
        }
        Integer quantity = spinner.getValue();


        Stock stock = new Stock(clothes,local,quantity,size,color);
        List<Stock> equalStocks = ss.getStockRepo().findByClothesAndAndColorAndAndLocalsAndAndSize(clothes,color,local,size);
        if(equalStocks.isEmpty()) {
            ss.save(stock);
        }else {
            ss.getStockRepo().updateQuantity(equalStocks.get(0).getId(),equalStocks.get(0).getQuantity() + quantity);
        }

        succesText.setText("Success!");

    }

    @FXML
    public void home(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(AdminController.class.getResource("Admin.fxml"));
        fxmlloader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlloader.load());
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}

