package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.FacheritAppApplication;
import tic1.grupo9.facheritApp.backend.services.ClotheSpecification;
import tic1.grupo9.facheritApp.backend.services.ClothesService;
import tic1.grupo9.facheritApp.commons.entities.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

@Controller
public class HombreController implements Initializable {

    @Autowired
    ClothesService cls;

    @FXML
    private Hyperlink mujer;

    @FXML
    private AnchorPane parteRopa;

    @FXML
    private GridPane gridPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ChoiceBox<String> typeBox;

    @FXML
    private ChoiceBox<String> colorBox;

    @FXML
    private ChoiceBox<String> sizeBox;

    @FXML
    private Spinner<Double> price1;

    @FXML
    private Spinner<Double> price2;



    private void showSelection(){
        typeBox.getItems().addAll(FXCollections.observableArrayList("pantalon","camisa","calzado","calzado deportivo","abrigo","buzo","medias",
                "ropa interior","bermuda","remera","accesorio","short"));
        colorBox.getItems().addAll(FXCollections.observableArrayList("negro","rojo","blanco","amarillo","azul","anaranjado"));
        sizeBox.getItems().addAll(FXCollections.observableArrayList("S","M","L","XL"));





    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showSelection();
        agregar(cls.getByGender("Masculino"));



    }

    @FXML
    public void mujer(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(LoginUserController.class.getResource("mujer.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }
    @FXML
    public void niños(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(LoginUserController.class.getResource("niños.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }


    private void agregar(List<Clothes> prenda){
        int count = 0;
        int temp = prenda.size()-1;
        for(int i =0; i< ((double) prenda.size())/2; i++) {
            count++;
            if (count > gridPane.getRowCount()) {
                RowConstraints con = new RowConstraints();
                con.setPrefHeight(190);
                gridPane.getRowConstraints().add(con);
            }
            for (int j = 0; j < 2; j++) {
                if(temp<0)return;
                VBox content = new VBox();
                gridPane.add(content, j, i);
                content.setAlignment(Pos.CENTER);
                content.setPadding(new Insets(5, 5, 5, 5));
                Clothes clothTemp = prenda.get(temp);
                ImageView image = clothTemp.getPicture();
                Button buyButton = new Button("Buy");
                Label textArea = new Label();
                textArea.setText(clothTemp.toString());
                content.getChildren().add(image);
                content.getChildren().add(textArea);
                content.getChildren().add(buyButton);
                temp--;
            }
        }

    }

    public void filter(javafx.event.ActionEvent actionEvent) throws IOException{
        String tipoSeleccionado = typeBox.getValue();
        String talleSeleccionado = sizeBox.getValue();
        String colorSeleccionado = colorBox.getValue();
        double precio1 = price1.getValue();
        double precio2 = price2.getValue();

        if(tipoSeleccionado!=null && talleSeleccionado==null && colorSeleccionado==null && precio1==0.0 && precio2==0.0){
            gridPane.getChildren().clear();
            System.out.println("hola");
            agregar(cls.getByGenderAndType("Masculino", tipoSeleccionado));
            return;
        }

        if(tipoSeleccionado!=null && talleSeleccionado!=null && colorSeleccionado==null && precio1==0.0 && precio2==0.0){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndTypeAndSize("Masculino", tipoSeleccionado, talleSeleccionado));
            return;
        }

        if(tipoSeleccionado!=null && talleSeleccionado!=null && colorSeleccionado!=null && precio1==0.0 && precio2==0.0){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndTypeAndSizeAndColor("Masculino", tipoSeleccionado, talleSeleccionado, colorSeleccionado));
            return;
        }

        if(tipoSeleccionado!=null && talleSeleccionado!=null && colorSeleccionado!=null && price1!=null && price2!=null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndTypeAndSizeAndColorAndPriceBetween("Masculino", tipoSeleccionado, talleSeleccionado, colorSeleccionado, precio1, precio2));
            return;
        }

        if(tipoSeleccionado==null && talleSeleccionado==null && colorSeleccionado==null && price1!=null && price2!=null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndPriceBetween("Masculino", precio1, precio2));
            return;
        }

        if(tipoSeleccionado!=null && talleSeleccionado==null && colorSeleccionado==null && price1!=null && price2!=null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndTypeAndPriceBetween("Masculino", tipoSeleccionado, precio1, precio2));
            return;
        }



    }


}
