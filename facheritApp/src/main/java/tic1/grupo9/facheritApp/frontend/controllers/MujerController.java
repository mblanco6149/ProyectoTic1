package tic1.grupo9.facheritApp.frontend.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.backend.services.ClothesService;
import tic1.grupo9.facheritApp.commons.entities.Clothes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class MujerController implements Initializable {

    @Autowired
    ClothesService cls;

    @FXML
    GridPane gridPane;

    @FXML
    private Hyperlink mujer;

    @FXML
    private AnchorPane parteRopa;

    @FXML
    private ChoiceBox<String> colourChoiceBox;

    private List<Clothes> womenClothes;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> colourList = new ArrayList<>();
        colourList.add("blanco");colourList.add("negro");colourList.add("azul");colourList.add("verde");colourList.add("rojo");colourList.add("beige");colourList.add("gris");
        ObservableList<String> colourObsList= FXCollections.observableArrayList(colourList);
        colourChoiceBox.setItems(colourObsList);
    }

    public void filtrar(List<Clothes> lista) {
        int count = 0;
        int temp = lista.size() - 1;
        for (int i = 0; i < (lista.size() / 2); i++) {
            count++;
            if (count > gridPane.getRowCount()) {
                RowConstraints con = new RowConstraints();
                con.setPrefHeight(190);
                gridPane.getRowConstraints().add(con);
            }
            for (int j = 0; j < 2; j++) {
                VBox content = new VBox();
                gridPane.add(content, j, i);
                content.setAlignment(Pos.CENTER);
                content.setPadding(new Insets(5, 5, 5, 5));
                Clothes clothTemp = lista.get(temp);
                ImageView image = clothTemp.getPicture();
                Button buyButton = new Button("Buy");
                Label textArea = new Label();
                textArea.setText(clothTemp.toString());
                content.getChildren().add(image);
                content.getChildren().add(textArea);
                content.getChildren().add(buyButton);
                System.out.println(clothTemp.getType());
                temp--;
            }
        }
    }


    public void pantalon(javafx.event.ActionEvent actionEvent) throws IOException {
        gridPane.getChildren().clear();
        List<Clothes> pantalones = cls.getByGenderAndType("Femenino", "pantalon");
        filtrar(pantalones);
    }

    public void abrigo(javafx.event.ActionEvent actionEvent) throws IOException {
        gridPane.getChildren().clear();
        List<Clothes> abrigos = cls.getByGenderAndType("Femenino", "abrigo");
        filtrar(abrigos);
    }

    public void vestido(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        List<Clothes> vestidos = cls.getByGenderAndType("Femenino", "vestido");
        filtrar(vestidos);
    }

    public void calzado(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        List<Clothes> calzados = cls.getByGenderAndType("Femenino", "calzado");
        filtrar(calzados);
    }

    public void buzo(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        List<Clothes> buzos = cls.getByGenderAndType("Femenino", "buzo");
        filtrar(buzos);
    }

    public void jean(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        List<Clothes> jeans = cls.getByGenderAndType("Femenino", "jean");
        filtrar(jeans);
    }

    public void camisa(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        List<Clothes> camisas = cls.getByGenderAndType("Femenino", "camisa");
        filtrar(camisas);
    }

    public void remera(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        List<Clothes> remeras = cls.getByGenderAndType("Femenino", "remera");
        filtrar(remeras);
    }

    public void shortt(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        List<Clothes> shorts = cls.getByGenderAndType("Femenino", "short");
        filtrar(shorts);
    }

    public void bermuda(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        List<Clothes> bermudas = cls.getByGenderAndType("Femenino", "bermuda");
        filtrar(bermudas);
    }

    public void ropaInterior(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        List<Clothes> tangas = cls.getByGenderAndType("Femenino", "ropainterior");
        filtrar(tangas);
    }

    public void calzadoDeportivo(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        List<Clothes> deportivos = cls.getByGenderAndType("Femenino", "deportivo");
        filtrar(deportivos);
    }

    public void media(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        List<Clothes> medias = cls.getByGenderAndType("Femenino", "medias");
        filtrar(medias);
    }

    public void accesorio(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        List<Clothes> accesorios = cls.getByGenderAndType("Femenino", "accesorio");
        filtrar(accesorios);
    }




}