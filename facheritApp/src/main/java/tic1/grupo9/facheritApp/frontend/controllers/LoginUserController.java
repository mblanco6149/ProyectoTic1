package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.backend.appi.BackendServiceImp;
import tic1.grupo9.facheritApp.backend.services.AdminService;
import tic1.grupo9.facheritApp.backend.services.ClientService;
import tic1.grupo9.facheritApp.backend.services.LocalService;
import tic1.grupo9.facheritApp.backend.services.UserService;
import tic1.grupo9.facheritApp.commons.entities.Client;
import tic1.grupo9.facheritApp.commons.entities.User;
import tic1.grupo9.facheritApp.commons.exceptions.ClientNoExist;
import tic1.grupo9.facheritApp.FacheritAppApplication;

import javafx.scene.control.TextField;
import tic1.grupo9.facheritApp.commons.exceptions.NoUserFound;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LoginUserController implements Initializable {

    @Autowired
    UserService us;

    @Autowired
    AdminService as;

    @Autowired
    ClientService cs;

    @Autowired
    LocalService ls;

    @Autowired
    BackendServiceImp bsi;

    @Autowired
    StartAppiController startAppiController;

    @FXML
    PasswordField password_textfield;

    @FXML
    TextField email_textfield;

    @FXML
    Text login_error;





    @FXML
    public void login(javafx.event.ActionEvent actionEvent) throws IOException, ClientNoExist, NoUserFound {
        User userTemp = new User(email_textfield.getText(),password_textfield.getText());

        User user = us.findByEmail(email_textfield.getText());


        if(user!=null && user.getPassword().equals(userTemp.getPassword()) ){
            FXMLLoader fxmlLoader = new FXMLLoader(StartAppiController.class.getResource("startAppi.fxml"));
            fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
            Scene tableViewScene = new Scene(fxmlLoader.load());


            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();

            // remover el boton de login/register en startAppi y poner Hola Manuel!
            Client client = cs.findByEmail(email_textfield.getText());
            startAppiController.changeLoginButton(client.getFirstName());
        }
        if(email_textfield.getText().equals("admin") && password_textfield.getText().equals("facheritapp2020")) {
            FXMLLoader fxmlloader = new FXMLLoader(AdminController.class.getResource("Admin.fxml"));
            fxmlloader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
            Scene tableViewScene = new Scene(fxmlloader.load());
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }
        else{
            login_error.setText("El email o contraseña son incorrectos");
            //Mostrar un text que diga que la contrasena y/o usuario son incorrectas.

        }
    }

    @FXML
    public void home(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(StartAppiController.class.getResource("startAppi.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    public void register(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterClientController.class.getResource("RegisterClient.fxml"));
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
