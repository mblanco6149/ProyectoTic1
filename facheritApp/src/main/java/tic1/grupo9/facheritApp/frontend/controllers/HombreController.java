package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import tic1.grupo9.facheritApp.commons.entities.Clothes;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

    private List<Clothes> masculineClothes;



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
    public void abrigos(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        agregar(cls.getByGenderAndType("Masculino", "abrigo"));

    }
    public void calzado(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        agregar(cls.getByGenderAndType("Masculino", "calzado"));

    }
    public void remera(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        agregar(cls.getByGenderAndType("Masculino", "remera"));

    }
    public void bermuda(javafx.event.ActionEvent actionEvent) throws IOException{
        gridPane.getChildren().clear();
        agregar(cls.getByGenderAndType("Masculino", "bermuda"));

    }

    private void agregar(List<Clothes> pantalones){
        System.out.println(pantalones.size());
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
                if(temp<0)return;
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


}
