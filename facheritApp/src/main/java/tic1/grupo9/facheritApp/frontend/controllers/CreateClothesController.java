package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.FacheritAppApplication;
import tic1.grupo9.facheritApp.backend.services.*;
import tic1.grupo9.facheritApp.commons.entities.Brand;
import tic1.grupo9.facheritApp.commons.entities.Clothes;
import tic1.grupo9.facheritApp.commons.entities.Colour;
import tic1.grupo9.facheritApp.commons.entities.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.List;

@Controller
public class CreateClothesController implements Initializable {

    @Autowired
    AdminController adminController;
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
    @Autowired
    ClothesService cs;




    @FXML
    TextField nameTxt;
    @FXML
    TextField typeTxt;
    @FXML
    TextField priceTxt;
    @FXML
    ChoiceBox<String> brandChoiceBox;
    @FXML
    ChoiceBox<String> genderChoiceBox;
    @FXML
    TextArea colorTxt;
    @FXML
    TextArea sizeTxt;
    @FXML
    ImageView imageView = new ImageView();
    @FXML
    Text imagetext;
    @FXML
    Text errorText;

    byte[] imageByteArray;


    FileChooser fileChooser = new FileChooser();

    List<Brand> brands;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        brands = adminController.getBrandList();

        fileChooser.setInitialDirectory(new File("C:\\temp"));
        showFields();
    }

    public void showFields(){

        List<String> brandList = new ArrayList<>();
        for (Brand b:brands) {
            brandList.add(b.getName());
        }
        ObservableList<String> brandsObservableList = FXCollections.observableArrayList(brandList);
        brandChoiceBox.setItems(brandsObservableList);

        List<String> genderList = new ArrayList<>();
        genderList.add("Masculino");
        genderList.add("Femenino");
        genderList.add("Niño");
        ObservableList<String> genderObservableList = FXCollections.observableArrayList(genderList);
        genderChoiceBox.setItems(genderObservableList);

    }


    public void selectImage(javafx.event.ActionEvent actionEvent) throws IOException, URISyntaxException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null){
            byte[] bytePicture = getBytesOfImage(selectedFile);
            imageByteArray = bytePicture;
            Image image=new Image(new ByteArrayInputStream(bytePicture), 140, 140, true, true);
            imageView.setImage(image);

        }else{
            imagetext.setStyle("-fx-text-fill: red; ");
            imagetext.setText("file is not valid");
        }
    }

    private byte[] getBytesOfImage(File file) throws IOException, URISyntaxException {
        BufferedImage bufferedPic = ImageIO.read(file);
        ByteArrayOutputStream picStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedPic, "jpg", picStream);
        return picStream.toByteArray();
    }

    public void addClothes(javafx.event.ActionEvent actionEvent){
        String name = nameTxt.getText();

        String type = typeTxt.getText();

        double price = Double.parseDouble(priceTxt.getText());

        String gender = genderChoiceBox.getValue();



        String sizeText = sizeTxt.getText();
        String colourText = colorTxt.getText();
        List<String> colourstringList = Arrays.asList(colourText.split("\\s*,\\s*"));
        List<Colour> colours = new ArrayList<>();
        Set<Size> sizes = new HashSet<>();

        if(name==null || type==null || priceTxt.getText()==null || gender==null || brandChoiceBox.getValue()==null || sizeText==null || colourText==null){
            errorText.setText("Not all fields are complete");
            errorText.setStyle("-fx-text-fill: red; ");
            return;
        }
        String brandString = brandChoiceBox.getValue();
        Brand brand = bs.getByName(brandString).get(0);

        Clothes clothes =  new Clothes(name,type,price,colours,sizes,gender,imageByteArray,brand);

        for(String s : colourstringList){
            Colour c = new Colour(s);
            c.setClothes(clothes);
            colours.add(c);

        }


        List<String> sizesList = Arrays.asList(sizeText.split("\\s*,\\s*"));
        for(String s : sizesList){
            Size sz = new Size(s);
            sz.setClothes_size(clothes);
            sizes.add(sz);

        }

        startAppiController.getClothesToShow().add(clothes);
        cs.save(clothes);

    }

    public void home(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(AdminController.class.getResource("Admin.fxml"));
        fxmlloader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlloader.load());
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
