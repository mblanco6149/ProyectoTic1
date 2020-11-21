package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.FacheritAppApplication;
import tic1.grupo9.facheritApp.backend.services.*;
import tic1.grupo9.facheritApp.commons.entities.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


@Controller
public class AdminController implements Initializable {

    @Autowired
    StartAppiController startAppiController;

    @Autowired
    BrandService bs;
    @Autowired
    ColourService cols;
    @Autowired
    SizeService szs;
    @Autowired
    StockService ss;
    @Autowired
    LocalService ls;


    private List<Brand> brandList;
    private List<Local> localList;

    public List<Brand> getBrandList() {
        return brandList;
    }




    public List<Local> getLocalList() {
        return localList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        if(brandList==null){

            brandList = bs.getBrandRepo().findAll();
        }

        if(localList==null){
            localList = ls.getLocalRepo().findAll();
        }
    }



    @FXML
    public void createLocal(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LocalController.class.getResource("Local.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public void createBrand(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BrandController.class.getResource("CreateBrand.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public void createStock(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StockController.class.getResource("LocalAddStock.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();


        window.show();
    }

    @FXML
    public void createNewProduct(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateClothesController.class.getResource("BrandCreateClothes.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.show();
    }

    @FXML
    public void returnToStart(javafx.event.ActionEvent actionEvent)throws  IOException{
        startAppiController.updateClothesFromAdmin();

        FXMLLoader fxmlLoader = new FXMLLoader(StartAppiController.class.getResource("startAppi.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void updateBrands (){
        this.brandList=bs.getBrandRepo().findAll();
    }

    public  void  updateLocals(){
        this.localList=ls.getLocalRepo().findAll();
    }
}
