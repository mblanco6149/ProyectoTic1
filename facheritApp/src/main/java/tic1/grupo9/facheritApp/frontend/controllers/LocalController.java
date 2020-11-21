package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.FacheritAppApplication;
import tic1.grupo9.facheritApp.backend.services.BrandService;
import tic1.grupo9.facheritApp.backend.services.LocalService;
import tic1.grupo9.facheritApp.commons.entities.Brand;
import tic1.grupo9.facheritApp.commons.entities.Local;


import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class LocalController implements Initializable {
    @Autowired
    AdminController adminController;

    @Autowired
    BrandService bs;
    @Autowired
    LocalService ls;

    @FXML
    TextField nameTxt;
    @FXML
    TextField passwordTxt;
    @FXML
    ComboBox<String> brandBox;
    @FXML
    ListView<String> brandListView;
    @FXML
    ListView<String> brandListViewToSave;

    ObservableList<String> brandStringListToSave ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showFields();
    }

    public void showFields(){
       // List<String> brands = new ArrayList<>();
        List<Brand> brandList = bs.getBrandRepo().findAll();
       /* for (Brand b:brandList) {
            brands.add(b.getName());
        }
        ObservableList<String> brandObservableList = FXCollections.observableArrayList(brands);
        brandBox.setItems(brandObservableList);*/

        ObservableList<String> brands = FXCollections.observableArrayList();
        for (Brand b:brandList) {
           brands.add(b.getName());
        }
        brandListView.setItems(brands);

        brandStringListToSave = FXCollections.observableArrayList();

        brandListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        brandListView.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
            if(!brandStringListToSave.contains(nv)) {
                brandStringListToSave.add(nv);
                brandListViewToSave.setItems(brandStringListToSave);
            }

        });


    }

    @FXML
    public void create(javafx.event.ActionEvent actionEvent){
        String name=nameTxt.getText();
        String password = passwordTxt.getText();


        Set<Brand> brands = new HashSet<>();
        Local local = new Local(name,password,brands);
        ls.save(local);
        for (String s:brandStringListToSave) {
            Brand brand = bs.getByName(s).get(0);
            local.addBrand(brand);
            brand.addLocal(local);
           // bs.getBrandRepo().deleteBrandByName(s);
            bs.save(brand);
        }


    }

    @FXML
    public void home(javafx.event.ActionEvent actionEvent) throws IOException {
        adminController.updateBrands();
        adminController.updateLocals();

        FXMLLoader fxmlloader = new FXMLLoader(AdminController.class.getResource("Admin.fxml"));
        fxmlloader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlloader.load());
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
