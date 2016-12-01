package ui.view.presentation.Marketer;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerFrame extends Application {

    private static String resource = "Marketer/MarketerFrame.fxml";

    private StageController stageController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stageController = new StageController();
        stageController.loadStage(resource, 1);

    }
}
