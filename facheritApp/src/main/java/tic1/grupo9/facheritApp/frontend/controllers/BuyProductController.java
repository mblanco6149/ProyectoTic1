package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.FacheritAppApplication;
import tic1.grupo9.facheritApp.backend.appi.BackendServiceImp;
import tic1.grupo9.facheritApp.backend.services.*;
import tic1.grupo9.facheritApp.commons.entities.Clothes;
import tic1.grupo9.facheritApp.commons.entities.Colour;
import tic1.grupo9.facheritApp.commons.entities.Size;
import tic1.grupo9.facheritApp.commons.entities.Stock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

@Controller
@EnableAutoConfiguration
public class BuyProductController implements Initializable {
    @Autowired
    AdminService as;

    @Autowired
    ClientService cs;

    @Autowired
    ClothesService cls;

    @Autowired
    LocalService ls;

    @Autowired
    StockService ss;

    @Autowired
    BrandService bs;

    @Autowired
    BackendServiceImp bsi;

    protected  Clothes clothes;




    @FXML
    Text clothesName;
    @FXML
    ImageView imageView;

    @FXML
    ChoiceBox<String> chooseColour;

    @FXML
    ChoiceBox<String> chooseSize;

    @FXML
    Button buyButton;

    @FXML
    Text message;

    @FXML
    Text priceText;

    @FXML
    Spinner<Integer> quantitySpinner;

    Boolean isLoggedIn = false;


    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showClothes();
    }

    public  void  showClothes (){

        Image image = new Image(new ByteArrayInputStream(clothes.getClothPicture()));
        imageView.setImage(image);

        clothesName.setText(clothes.getName());
        String price=String.valueOf(clothes.getPrice());
        String brand = clothes.getBrand().getName();
        priceText.setText("precio: $"+ price + "\nMarca: "+brand);

        List<Colour> colours = clothes.getColor();
        List<String> colourNames = new ArrayList<>();
        for (int i=0; i<colours.size();i++){
            colourNames.add(colours.get(i).getColours());
        }
        ObservableList<String> colourList = FXCollections.observableArrayList(colourNames);
        chooseColour.setItems(colourList);

        Set<Size> sizes = clothes.getSize();
        List<String> sizeNames = new ArrayList<>();
        for (Size s : sizes){
            sizeNames.add(s.getSizes());
        }
        ObservableList<String> sizesList = FXCollections.observableArrayList(sizeNames);
        chooseSize.setItems(sizesList);

    }

    @FXML
    public  void buy(javafx.event.ActionEvent actionEvent) {

        if(isLoggedIn==false){
            message.setText("Please, Log in first");
        }else {
            int quantityToBuy = quantitySpinner.getValue();
            String color = chooseColour.getSelectionModel().getSelectedItem();
            String size = chooseSize.getSelectionModel().getSelectedItem();
            List<Stock> stocks = ss.getStockRepo().findByClothes(clothes);

            for (int i = 0; i < stocks.size(); i++) {
                Stock stock = stocks.get(i);
                if (stock.getColor().equals(color) && stock.getSize().equals(size) && stock.getSize().equals(size)) {
                    if (stock.getQuantity() >= quantityToBuy) {
                        //stock.setQuantity((stock.getQuantity() - 1));
                        buyButton.setText("thanks");
                        message.setText("Your purchase was succesful.");

                        ss.getStockRepo().updateQuantity(stock.getId(), stock.getQuantity() - quantityToBuy);
                        return;
                    } else {
                        message.setText("No stock available.");
                        return;
                    }

                }

            }
            message.setText("No stock found for selected size and color.");
            return;
        }
    }

    @FXML
    public void goHome(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartAppiController.class.getResource("startAppi.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
