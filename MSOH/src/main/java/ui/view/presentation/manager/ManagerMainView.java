package ui.view.presentation.manager;/**
 * Created by island on 2016/11/21.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import ui.view.presentation.StageController;

public class ManagerMainView extends Application {

    private static String resources = "manager/ManagerMainView.fxml";

    private StageController stageController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stageController = new StageController();
        stageController.loadStage(resources, 1);
    }
}
