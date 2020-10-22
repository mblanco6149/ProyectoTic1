package tic1.grupo9.facheritApp.frontend.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.backend.appi.BackendServiceImp;
import tic1.grupo9.facheritApp.backend.services.*;
import tic1.grupo9.facheritApp.commons.entities.Client;
import tic1.grupo9.facheritApp.FacheritAppApplication;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


@Controller
@EnableAutoConfiguration
public class RegisterClientController implements Initializable {

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
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhone;


    @FXML
    public void register(javafx.event.ActionEvent actionEvent) throws Throwable {

        Optional<Client> optionalCliente = cs.getClientRepo().findById(this.txtEmail.getText());
        if (optionalCliente.isPresent()){
             throw new Throwable("Usuario no disponible");
        }else{
            Client cliente = new Client(txtEmail.getText(),txtPassword.getText(),txtName.getText(),txtSurname.getText(),Long.parseLong(txtPhone.getText()),txtAddress.getText());

            cs.save(cliente);

            FXMLLoader fxmlLoader = new FXMLLoader(StartAppiController.class.getResource("startAppi.fxml"));
            fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
            Scene tableViewScene = new Scene(fxmlLoader.load());

            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }


    }








    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}