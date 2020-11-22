package tic1.grupo9.facheritApp.frontend.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import tic1.grupo9.facheritApp.FacheritAppApplication;
import tic1.grupo9.facheritApp.backend.services.StockService;
import tic1.grupo9.facheritApp.commons.entities.Clothes;
import tic1.grupo9.facheritApp.commons.entities.Stock;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
@EnableAutoConfiguration
public class CarritoControler implements Initializable {
    @Autowired
    private StockService ss;
    @FXML
    private Text emptyError;
    @FXML
    private GridPane grid;

    List<Stock> stocksToBuy = new ArrayList<>();
    List<Integer> quantityToSubstract = new ArrayList<>();

    Integer pagina=0;


    public List<Stock> getStocksToBuy() {
        return stocksToBuy;
    }

    public List<Integer> getQuantityToSubstract() {
        return quantityToSubstract;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showItems();
    }

    public void showItems(){
        int k = stocksToBuy.size()-1-12*pagina;
        for(int i=0;i<12;i++){
            if(k<0){
                return;
            }
            Stock stock = stocksToBuy.get(k);
            if(stock==null){
                return;
            }
            VBox content = new VBox();
            content.setAlignment(Pos.CENTER);
            content.setPadding(new Insets(5, 0, 2, 0));

            Button deleteButton = new Button("delete");
            Label textArea = new Label();
            textArea.setText(stock.getClothes().getName()+", size: "+stock.getSize()+", color: "+stock.getColor()+", quantity: "+quantityToSubstract.get(k).toString() + " (local: " + stock.getLocals().getName() +")");
            content.getChildren().add(textArea);
            content.getChildren().add(deleteButton);

            int a=k;

            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {

                    try {
                        delete(a,e);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            grid.add(content,1,i);
            k--;


        }

    }

    @FXML
    public void buyAll(javafx.event.ActionEvent actionEvent){
        if(stocksToBuy.isEmpty()){
            emptyError.setText("Empty cart");
            emptyError.setStyle("-fx-text-fill: red; ");
        }else{
            for (int i=0;i<stocksToBuy.size(); i++) {
                Stock s= stocksToBuy.get(i);
                Integer quantity = quantityToSubstract.get(i);
                ss.getStockRepo().updateQuantity(s.getId(), s.getQuantity() - quantity);
            }

            stocksToBuy.clear();
            quantityToSubstract.clear();
            grid.getChildren().clear();
            showItems();
        }
    }

    @FXML
    public void delete(int i,javafx.event.ActionEvent actionEvent) throws IOException{
        this.quantityToSubstract.remove(i);
        this.stocksToBuy.remove(i);
        grid.getChildren().clear();
        showItems();
    }


    public void next(javafx.event.ActionEvent actionEvent) throws IOException{
        if((pagina+1)*12<stocksToBuy.size()) {
            pagina = pagina + 1;
            grid.getChildren().clear();
            this.showItems();
        }
    }

    public void back(javafx.event.ActionEvent actionEvent)throws  IOException{
        if(pagina>0){
            pagina =pagina-1;
            grid.getChildren().clear();
            this.showItems();
        }
    }

    public void home(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartAppiController.class.getResource("startAppi.fxml"));
        fxmlLoader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
        Scene tableViewScene = new Scene(fxmlLoader.load());

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
