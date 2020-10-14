package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.backend.appi.BackendServiceImp;
import tic1.grupo9.facheritApp.backend.services.AdminService;
import tic1.grupo9.facheritApp.backend.services.ClientService;
import tic1.grupo9.facheritApp.backend.services.LocalService;
import tic1.grupo9.facheritApp.commons.entities.Client;
import tic1.grupo9.facheritApp.commons.exceptions.ClientNoExist;
import tic1.grupo9.facheritApp.frontend.FacheritAppApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.hibernate.hql.internal.antlr.SqlTokenTypes.FROM;
import static org.hibernate.loader.Loader.SELECT;

@Controller
@EnableAutoConfiguration
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





    public void login(javafx.event.ActionEvent actionEvent) throws IOException, ClientNoExist {
        Client client = null;
        client = cs.findByEmail(email_textfield.getText());



    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
