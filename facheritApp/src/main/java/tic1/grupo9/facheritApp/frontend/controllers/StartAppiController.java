package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.backend.appi.BackendServiceImp;
import tic1.grupo9.facheritApp.backend.services.*;
import tic1.grupo9.facheritApp.commons.entities.Clothes;
import tic1.grupo9.facheritApp.FacheritAppApplication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Data
@Controller
@EnableAutoConfiguration
public class StartAppiController implements Initializable {

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

    @Autowired
    BuyProductController bpc;

    @Autowired
    CarritoControler carrito;

    @FXML
    private Button ingresar_reg_button;

    @FXML
    private GridPane grid;

    @FXML
    private Text cartCount;

    private int pagina=0;
    private  int cantidad_clothes ;
    private List<Clothes> clothesToShow;
    private Boolean isLoggedIn =false;

    public List<Clothes> getClothesToShow() {
        return clothesToShow;
    }

    public void setClothesToShow(List<Clothes> clothesToShow) {
        this.clothesToShow = clothesToShow;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cartCount.setText(String.valueOf(carrito.getQuantityToSubstract().size()) );
        if(clothesToShow==null){
            clothesToShow = cls.getClothesRepo().findAll();
        }
        cantidad_clothes = clothesToShow.size();
        agregarCLothes();




    }

    public void changeLoginButton(String user_name){

        ingresar_reg_button.setText("Hola " + user_name + "!");
        ingresar_reg_button.setDisable(true);

    }


    public void agregarCLothes() {

        int k = clothesToShow.size()-1-15*pagina;
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 3; j++) {
                    if(k<0){
                        return;
                    }
                    Clothes clothTemp = clothesToShow.get(k);
                    if(clothTemp == null) {
                        return;
                    }
                    VBox content = new VBox();
                    content.setAlignment(Pos.CENTER);
                    content.setPadding(new Insets(5, 0, 2, 0));
                    ImageView image1 = clothTemp.getPicture();


                    Button buyButton = new Button("Buy");
                    Label textArea = new Label();
                    textArea.setText(clothTemp.toString());
                    content.getChildren().add(image1);
                    content.getChildren().add(textArea);
                    content.getChildren().add(buyButton);

                    int a=k;

                    buyButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent e) {

                            try {
                                buy(a,e);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                        }
                    });

                    grid.add(content, j, i);
                    k--;
                }
            }





    }


    public void buy(int i,javafx.event.ActionEvent actionEvent) throws IOException {

        List<Clothes> clothesToShow = cls.getClothesRepo().findAll();
        Clothes clothes = clothesToShow.get(i);
        bpc.setClothes(clothes);

        FXMLLoader fxmlLoader = new FXMLLoader(BuyProductController.class.getResource("BuyProduct.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);


        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

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


    @FXML
    public void hombre(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HombreController.class.getResource("hombre.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    public void mujer(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MujerController.class.getResource("mujer.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    public void niños(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(NiñosController.class.getResource("niños.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void carrito(javafx.event.ActionEvent actionEvent) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(CarritoControler.class.getResource("Carrito.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }

    public void next(javafx.event.ActionEvent actionEvent) throws IOException{
        if((pagina+1)*15<cantidad_clothes) {
            pagina = pagina + 1;
            grid.getChildren().clear();
            this.agregarCLothes();
        }
    }

    public void back(javafx.event.ActionEvent actionEvent)throws  IOException{
        if(pagina>0){
            pagina =pagina-1;
            grid.getChildren().clear();
            this.agregarCLothes();
        }
    }

    public void updateClothesFromAdmin(){
        this.clothesToShow=cls.getClothesRepo().findAll();;
    }



}
