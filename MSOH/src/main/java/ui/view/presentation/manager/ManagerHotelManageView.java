package ui.view.presentation.manager;/**
 * Created by island on 2016/11/22.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.view.presentation.customer.CustomerMainView;

import java.io.IOException;


public class ManagerHotelManageView extends Application {
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

        //showCustomerMainView();
    }

    public void initPane() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ManagerHotelManageView.class.getResource("ManagerHotelManageView.fxml"));
            pane = (Pane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
