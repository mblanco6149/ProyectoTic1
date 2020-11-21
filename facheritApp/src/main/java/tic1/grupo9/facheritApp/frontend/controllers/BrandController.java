package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.FacheritAppApplication;
import tic1.grupo9.facheritApp.backend.services.BrandService;
import tic1.grupo9.facheritApp.commons.entities.Brand;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class BrandController implements Initializable {
    @Autowired
    AdminController adminController;

    @Autowired
    BrandService bs;

    @FXML
    TextField nameTxt;
    @FXML
    TextField passwordTxt;
    @FXML
    Text errorText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void create(javafx.event.ActionEvent actionEvent){

        if(bs.getByName(nameTxt.getText()).isEmpty()){
            Brand brand = new Brand(nameTxt.getText(),passwordTxt.getText());
            bs.save(brand);
        }else{
            errorText.setText("Brand already exists");
        }
    }

    public void home(javafx.event.ActionEvent actionEvent) throws IOException {
        adminController.updateBrands();

        FXMLLoader fxmlloader = new FXMLLoader(AdminController.class.getResource("Admin.fxml"));
        fxmlloader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlloader.load());
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
