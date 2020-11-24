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


    private List<Clothes> masculineClothes;
    private ObservableList<String> typesList;

    public void showSelection(){
        typesList.addAll("pantalon","camisa","calzado","calzado deportivo","abrigo","buzo","medias",
                "ropa interior","bermuda","remera","accesorio","short");
        typeBox.setItems(typesList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        masculineClothes = cls.getByGender("Masculino");
        int temp = masculineClothes.size()-1;
        //scrollPane.setContent(gridPane);
        System.out.println(gridPane.getRowCount());

        for(int i =0; i<(masculineClothes.size()/2); i++){
            for(int j=0; j<2; j++){
                VBox content = new VBox();
                gridPane.add(content,j,i);
                content.setAlignment(Pos.CENTER);
                content.setPadding(new Insets(5, 5, 5, 5));
                Clothes clothTemp = masculineClothes.get(temp);
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

        /*VBox content = new VBox();
        gridPane.add(content,0,0);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(5, 0, 2, 0));
        Clothes clothTemp = masculineClothes.get(0);
        ImageView image = cloth.getPicture();
        Button buyButton = new Button("Buy");
        Label textArea = new Label();
        textArea.setText(cloth.toString());
        content.getChildren().add(image);
        content.getChildren().add(textArea);
        content.getChildren().add(buyButton);
        */








        showSelection();

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

    public void pantalon(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        agregar(cls.getByGenderAndType("Masculino", "pantalon"));
       // List<Clothes> pantalones2 = cls.getClothesRepo().findAll(ClotheSpecification.builder().gender("Masculino").type("pantalon").build());

    }

    public void camisa(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        agregar(cls.getByGenderAndType("Masculino", "camisa"));

    }

    private void agregar(List<Clothes> pantalones){
        int count = 0;
        int temp = pantalones.size()-1;
        for(int i =0; i<(pantalones.size()/2); i++) {
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
                Clothes clothTemp = pantalones.get(temp);
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

    public void filter(javafx.event.ActionEvent actionEvent) throws IOException{
        String tipoSeleccionado = typeBox.getSelectionModel().getSelectedItem();
        String talleSeleccionado = sizeBox.getSelectionModel().getSelectedItem();
        String colorSeleccionado = colorBox.getSelectionModel().getSelectedItem();
        double precio1 = price1.getValue();
        double precio2 = price2.getValue();

        if(tipoSeleccionado!=null && talleSeleccionado==null && colorSeleccionado==null && price1==null && price2==null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndType("Masculino", tipoSeleccionado));
        }

        if(tipoSeleccionado!=null && talleSeleccionado!=null && colorSeleccionado==null && price1==null && price2==null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndTypeAndSize("Masculino", tipoSeleccionado, talleSeleccionado));
        }

        if(tipoSeleccionado!=null && talleSeleccionado!=null && colorSeleccionado!=null && price1==null && price2==null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndTypeAndSizeAndColor("Masculino", tipoSeleccionado, talleSeleccionado, colorSeleccionado));
        }

        if(tipoSeleccionado!=null && talleSeleccionado!=null && colorSeleccionado!=null && price1!=null && price2!=null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndTypeAndSizeAndColorAndPriceBetween("Masculino", tipoSeleccionado, talleSeleccionado, colorSeleccionado, precio1, precio2));
        }

        if(tipoSeleccionado==null && talleSeleccionado==null && colorSeleccionado==null && price1!=null && price2!=null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndPriceBetween("Masculino", precio1, precio2));
        }

        if(tipoSeleccionado!=null && talleSeleccionado==null && colorSeleccionado==null && price1!=null && price2!=null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndTypeAndPriceBetween("Masculino", tipoSeleccionado, precio1, precio2));
        }



    }


}
