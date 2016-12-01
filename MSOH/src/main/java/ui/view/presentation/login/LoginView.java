package ui.view.presentation.login;/**
 * Created by ST on 2016/11/15.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView extends Application {

    private Stage primaryStage;
    private Pane pane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Octopus");

        initPane();

    }

    public void initPane() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginView.class.getResource("LoginView.fxml"));
            pane = (Pane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Give the controller access to LoginView class.
            LoginViewController controller = loader.getController();
            controller.setLoginView(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
