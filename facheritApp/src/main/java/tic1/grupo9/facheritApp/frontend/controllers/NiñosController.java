package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.FacheritAppApplication;
import tic1.grupo9.facheritApp.backend.services.BrandService;
import tic1.grupo9.facheritApp.backend.services.ClothesService;
import tic1.grupo9.facheritApp.commons.entities.Clothes;
import tic1.grupo9.facheritApp.commons.entities.Colour;
import tic1.grupo9.facheritApp.commons.entities.Size;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@EnableAutoConfiguration
@Controller
public class NiñosController  implements Initializable {
    @Autowired
    ClothesService cls;
    @Autowired
    BuyProductController bpc;

    @Autowired
    BrandService bs;

    @FXML
    private Hyperlink mujer;

    @FXML
    private GridPane gridPane;

    @FXML
    private ChoiceBox<String> typeBoxNiño;

    @FXML
    private ChoiceBox<String> colorBoxNiño;

    @FXML
    private ChoiceBox<String> sizeBoxNiño;

    @FXML
    private ChoiceBox<String> marcaBox;

    @FXML
    private Spinner<Double> price1;

    @FXML
    private Spinner<Double> price2;

    private List<Clothes> clothesList;

    private void showSelection(){
        typeBoxNiño.getItems().addAll(FXCollections.observableArrayList("pantalon","camisa","calzado","calzado deportivo","abrigo","buzo","medias",
                "ropa interior","bermuda","remera","accesorio","short"));
        typeBoxNiño.getSelectionModel().selectedItemProperty().addListener(
                (v,oldvalue,newv) -> {
                    if(newv=="calzado" || newv =="calzado deportivo"){
                        sizeBoxNiño.getItems().clear();
                        sizeBoxNiño.getItems().addAll(FXCollections.observableArrayList("38","39","40","41","42"));
                    }else{
                        sizeBoxNiño.getItems().clear();
                        sizeBoxNiño.getItems().addAll(FXCollections.observableArrayList("S","M","L","XL"));
                    }
                }
        );
        colorBoxNiño.getItems().addAll(FXCollections.observableArrayList("negro","rojo","blanco","amarillo","azul","anaranjado"));




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showSelection();
        clothesList= cls.getByGender("Niño");
        agregar(clothesList);
    }

    @FXML
    public void mujer(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MujerController.class.getResource("mujer.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    public void home(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartAppiController.class.getResource("startAppi.fxml"));
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


    private void agregar(List<Clothes> prenda){
        int count = 0;
        int temp = prenda.size()-1;
        for(int i =0; i< ((double) prenda.size())/2; i++) {
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
                Clothes clothTemp = prenda.get(temp);
                ImageView image = clothTemp.getPicture();
                Button buyButton = new Button("Buy");

                buyButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {

                        try {
                            buy(clothTemp,e);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }
                });

                Label textArea = new Label();
                textArea.setText(clothTemp.toString());
                content.getChildren().add(image);
                content.getChildren().add(textArea);
                content.getChildren().add(buyButton);
                temp--;
            }
        }

    }
    public void buy(Clothes c,javafx.event.ActionEvent actionEvent) throws IOException {


        bpc.setClothes(c);

        FXMLLoader fxmlLoader = new FXMLLoader(BuyProductController.class.getResource("BuyProduct.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);


        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void filter(javafx.event.ActionEvent actionEvent) throws IOException{
        String tipoSeleccionado = typeBoxNiño.getValue();
        String talleSeleccionado = sizeBoxNiño.getValue();
        String colorSeleccionado = colorBoxNiño.getValue();
        String marcaSeleccionado = marcaBox.getValue();
        double precio1 = price1.getValue();
        double precio2 = price2.getValue();

        if(tipoSeleccionado!=null && talleSeleccionado==null && colorSeleccionado==null && precio1==0.0 && precio2==0.0 && marcaSeleccionado==null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndType("Niño", tipoSeleccionado));
            return;
        }

        if(tipoSeleccionado==null && talleSeleccionado==null && colorSeleccionado==null && precio1==0.0 && precio2==0.0 && marcaSeleccionado==null){
            gridPane.getChildren().clear();
            agregar(cls.getByGender("Niño"));
            return;
        }

        if(tipoSeleccionado!=null && talleSeleccionado!=null && colorSeleccionado==null && precio1==0.0 && precio2==0.0 && marcaSeleccionado==null){
            gridPane.getChildren().clear();
            List<Clothes> list = cls.getByGenderAndType("Niño", tipoSeleccionado);
            filterBySize(list,talleSeleccionado);
            agregar(list);
            return;
        }

        if(tipoSeleccionado!=null && talleSeleccionado!=null && colorSeleccionado!=null && precio1==0.0 && precio2==0.0 && marcaSeleccionado==null){
            gridPane.getChildren().clear();
            List<Clothes> list = cls.getByGenderAndType("Niño", tipoSeleccionado);
            filterByColour(list,colorSeleccionado);
            filterBySize(list,talleSeleccionado);
            agregar(list);
            return;
        }

        if(tipoSeleccionado!=null && talleSeleccionado!=null && colorSeleccionado!=null && price1!=null && price2!=null && marcaSeleccionado==null){
            gridPane.getChildren().clear();
            List<Clothes> list = cls.getByGenderAndTypeAndPriceBetween("Niño", tipoSeleccionado,precio1, precio2);

            filterByColour(list,colorSeleccionado);
            filterBySize(list,talleSeleccionado);
            agregar(list);
            return;
        }

        if(tipoSeleccionado==null && talleSeleccionado==null && colorSeleccionado==null && price1!=null && price2!=null && marcaSeleccionado==null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndPriceBetween("Niño", precio1, precio2));
            return;
        }

        if(tipoSeleccionado!=null && talleSeleccionado==null && colorSeleccionado==null && price1!=null && price2!=null && marcaSeleccionado==null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndTypeAndPriceBetween("Niño", tipoSeleccionado, precio1, precio2));
            return;
        }

        if(tipoSeleccionado!=null && talleSeleccionado==null && colorSeleccionado!=null && price1==null && price2==null && marcaSeleccionado==null){
            gridPane.getChildren().clear();
            List<Clothes> list = cls.getByGenderAndType("Niño", tipoSeleccionado);
            filterByColour(list,colorSeleccionado);
            agregar(list);
            return;
        }
        if(tipoSeleccionado!=null && talleSeleccionado!=null && colorSeleccionado==null && price1!=null && price2!=null && marcaSeleccionado==null){
            gridPane.getChildren().clear();
            List<Clothes> list = cls.getByGenderAndTypeAndPriceBetween("Niño", tipoSeleccionado,precio1,precio2);
            filterBySize(list, talleSeleccionado);
            agregar(list);
            return;
        }
        if(tipoSeleccionado==null && talleSeleccionado==null && colorSeleccionado==null && precio1==0.0 && precio2==0.0 && marcaSeleccionado!=null){
            gridPane.getChildren().clear();
            agregar(cls.getByBrand("Niño", bs.getByName(marcaSeleccionado).get(0)));
            return;
        }
        if(tipoSeleccionado!=null && talleSeleccionado==null && colorSeleccionado==null && precio1==0.0 && precio2==0.0 && marcaSeleccionado!=null){
            gridPane.getChildren().clear();
            agregar(cls.getByTypeAndBrand("Niño",tipoSeleccionado,bs.getByName(marcaSeleccionado).get(0)));
            return;
        }
        if(tipoSeleccionado!=null && talleSeleccionado==null && colorSeleccionado==null && price1!=null && price2!=null && marcaSeleccionado!=null){
            gridPane.getChildren().clear();
            agregar(cls.getByTypeAndBrandAndPrice("Niño",tipoSeleccionado, bs.getByName(marcaSeleccionado).get(0), precio1, precio2));
            return;
        }
        if(tipoSeleccionado!=null && talleSeleccionado!=null && colorSeleccionado==null && price1!=null && price2!=null && marcaSeleccionado!=null){
            gridPane.getChildren().clear();
            List<Clothes> list = cls.getByTypeAndBrandAndPrice("Niño", tipoSeleccionado,bs.getByName(marcaSeleccionado).get(0),precio1,precio2);
            filterBySize(list, talleSeleccionado);
            agregar(list);
            return;
        }
        if(tipoSeleccionado!=null && talleSeleccionado==null && colorSeleccionado!=null && price1==null && price2==null && marcaSeleccionado!=null){
            gridPane.getChildren().clear();
            List<Clothes> list = cls.getByTypeAndBrandAndPrice("Niño", tipoSeleccionado,bs.getByName(marcaSeleccionado).get(0),precio1,precio2);
            filterByColour(list,colorSeleccionado);
            agregar(list);
            return;
        }
        if(tipoSeleccionado==null && talleSeleccionado==null && colorSeleccionado==null && price1!=null && price2!=null && marcaSeleccionado!=null){
            gridPane.getChildren().clear();
            agregar(cls.getByGenderAndBrandAndPrice("Niño", bs.getByName(marcaSeleccionado).get(0), precio1, precio2));
            return;
        }
    }

    public void filterByColour(List<Clothes> list,String colorSeleccionado){

        for (Clothes c :clothesList){
            boolean hasColour = false;
            for(Colour colour : c.getColor()){
                if(colour.getColours().equals(colorSeleccionado)){
                    hasColour=true;
                }
            }
            if(hasColour==false) {
                if (list.contains(c)) {
                    list.remove(c);
                }
            }
        }


    }

    public  void filterBySize(List<Clothes> list , String talleSeleccionado){
        for (Clothes c :clothesList){
            boolean hasSize = false;
            for(Size s : c.getSize()){
                if(s.getSizes().equals(talleSeleccionado)){
                    hasSize=true;
                }
            }
            if(hasSize==false) {
                if (list.contains(c)) {
                    list.remove(c);
                }
            }
        }
    }

    @FXML
    public void carrito(javafx.event.ActionEvent actionEvent) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(CarritoControler.class.getResource("Carrito.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

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

}
