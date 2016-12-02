package ui.view.presentation.clerk; /**
 * Created by ST on 2016/11/10.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import ui.view.presentation.StageController;

public class ClerkFrame extends Application {

    private static String resource = "clerk/ClerkFrame.fxml";

    private StageController stageController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stageController = new StageController();
        stageController.loadStage(resource, 1);
    }

//    public void initPane() {
//        try {
//            // Load root layout from fxml file.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(ClerkFrame.class.getResource("ClerkFrame.fxml"));
//            pane = (Pane) loader.load();
//
//            // Show the scene containing the root layout.
//            Scene scene = new Scene(pane);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Shows the hotelInfoView inside the root layout.
//     */
//    public void showHotelInfoView() {
//        try {
//            // Load hotelInfoView.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(ClerkFrame.class.getResource("ClerkHotelInfo.fxml"));
//            Pane hotelInfoview = (Pane) loader.load();
//
//            // Set hotelInfoView into the initial pane.
////            hotelInfoview.setVisible(true);
//            pane.getChildren().add(hotelInfoview);
//            hotelInfoview.setLayoutX(341);
//            hotelInfoview.setLayoutY(140);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public Stage getPrimaryStage() {
//        return primaryStage;
//    }
}