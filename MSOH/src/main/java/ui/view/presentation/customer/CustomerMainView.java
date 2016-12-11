package ui.view.presentation.customer;/**
 * Created by qky on 2016/11/14.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import ui.view.presentation.StageController;

public class CustomerMainView extends Application {
    private static String resource = "customer/CustomerMainView.fxml";

    private StageController stageController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stageController = new StageController();
        stageController.loadStage(resource, 1);
        CustomerMainViewController customerMainViewController = (CustomerMainViewController) stageController.getController();
        customerMainViewController.init2("111");
    }
}

