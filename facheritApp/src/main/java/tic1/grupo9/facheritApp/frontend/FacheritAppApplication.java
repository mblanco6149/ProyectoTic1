package tic1.grupo9.facheritApp.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tic1.grupo9.facheritApp.commons.entities.Admin;
import tic1.grupo9.facheritApp.commons.entities.User;

@SpringBootApplication
public class FacheritAppApplication extends Application {

	@Getter
	private static ConfigurableApplicationContext appiContext;
	private static Parent rootNode;
	@Getter
	private static Stage mainStage;

	public static ConfigurableApplicationContext getAppiContext() {
		return appiContext;
	}

	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void init() throws Exception {
		appiContext = SpringApplication.run(FacheritAppApplication.class);
	}

	@Override
	public void start(Stage stage) throws Exception {
		mainStage = stage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("controllers/startAppi.fxml"));
		loader.setControllerFactory(FacheritAppApplication.getAppiContext()::getBean);
		rootNode = loader.load();

		stage.setScene(new Scene(rootNode));
		stage.show();
	}


}
