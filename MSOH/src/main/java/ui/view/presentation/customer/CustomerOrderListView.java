package ui.view.presentation.customer;/**
 * Created by qky on 2016/11/16.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import ui.view.presentation.StageController;


public class CustomerOrderListView extends Application {
    public static String resource = "CustomerOrderListView.fxml";

    private StageController stageController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stageController = new StageController();
        stageController.loadStage(resource);

    }

}

