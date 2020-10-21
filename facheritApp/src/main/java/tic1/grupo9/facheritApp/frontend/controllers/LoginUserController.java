package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.backend.appi.BackendServiceImp;
import tic1.grupo9.facheritApp.backend.services.AdminService;
import tic1.grupo9.facheritApp.backend.services.ClientService;
import tic1.grupo9.facheritApp.backend.services.LocalService;
import tic1.grupo9.facheritApp.commons.entities.Client;
import tic1.grupo9.facheritApp.commons.exceptions.ClientNoExist;
import tic1.grupo9.facheritApp.FacheritAppApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LoginUserController implements Initializable {

    @Autowired
    AdminService as;

    @Autowired
    ClientService cs;

    @Autowired
    LocalService ls;

    @Autowired
    BackendServiceImp bsi;

    @FXML
    TextField password_textfield;

    @FXML
    TextField email_textfield;

    @FXML
    Text login_error;






    public void login(javafx.event.ActionEvent actionEvent) throws IOException, ClientNoExist {
        Client client = null;
        client = cs.findByEmail(email_textfield.getText());

        if(client!= null && client.getPassword().equals(password_textfield) ){
            FXMLLoader fxmlLoader = new FXMLLoader(FacheritAppApplication.class.getResource("startAppi.fxml"));
            fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
            Scene tableViewScene = new Scene(fxmlLoader.load());

            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            // remover el boton de login/register en startAppi y poner Hola Manuel!
            window.setScene(tableViewScene);
            window.show();
        }
        else{
            login_error.setText("El email o contraseña son incorrectos");
            //Mostrar un text que diga que la contrasena y/o usuario son incorrectas.

        }
    }

    public void register(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FacheritAppApplication.class.getResource("RegisterClient.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
