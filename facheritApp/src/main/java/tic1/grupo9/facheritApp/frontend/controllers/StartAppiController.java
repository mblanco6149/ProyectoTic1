package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.backend.appi.BackendServiceImp;
import tic1.grupo9.facheritApp.backend.services.*;
import tic1.grupo9.facheritApp.commons.entities.Clothes;
import tic1.grupo9.facheritApp.FacheritAppApplication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    @Autowired
    BackendServiceImp bsi;

    @FXML
    private GridPane grid;

    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        agregarCLothes();

    }


    public void agregarCLothes() {
        List<Clothes> clothesToShow = cls.getClothesRepo().findAll();
        int k = clothesToShow.size()-1;
            for (int i = 0; i < 11; i++){
                for (int j = 0; j < 3; j++) {
                    if(k<0){
                        return;
                    }
                    Clothes clothTemp = clothesToShow.get(k);
                    if(clothTemp == null) {
                        return;
                    }
                    VBox content = new VBox();
                    content.setAlignment(Pos.CENTER);
                    content.setPadding(new Insets(5, 0, 2, 0));
                    ImageView image1 = clothTemp.getPicture();


                    Text text = new Text();
                    text.setText(clothTemp.toString());
                    content.getChildren().add(image1);
                    content.getChildren().add(text);
                    grid.add(content, j, i);
                    k--;
                }
            }





        /*
        for(Clothes clothTemp: clothesToShow){

            if(clothTemp==null){

            }
            VBox content = new VBox();
            content.setAlignment(Pos.CENTER);
            content.setPadding(new Insets(5, 0, 2, 0));
            ImageView image1 = clothTemp.getPicture();


            TextArea textArea = new TextArea();
            textArea.setText(cls.getByLastId().toString());
            content.getChildren().add(image1);
            content.getChildren().add(textArea);
            grid.add(content,1,1);
         }*/

    }

    @FXML
    public void login(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(LoginUserController.class.getResource("loginUser.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }


    @FXML
    public void hombre(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HombreController.class.getResource("hombre.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    public void mujer(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MujerController.class.getResource("mujer.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    public void niños(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(NiñosController.class.getResource("niños.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void carrito(javafx.event.ActionEvent actionEvent) throws IOException{
        if(bsi.carritoIsEmpty()){

        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(FacheritAppApplication.class.getResource("Carrito.fxml"));
            fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
            Scene tableViewScene = new Scene(fxmlLoader.load());

            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }
    }


}
