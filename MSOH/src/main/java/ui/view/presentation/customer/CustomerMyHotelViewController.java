package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

import java.io.IOException;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerMyHotelViewController implements ControlledStage {
    StageController stageController;

    private String resources = "customer/CustomerMyHotelView.fxml";

    private CustomerSingleHotelViewController customerSingleHotelViewController;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane hotelListScrollPane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }

    public void addHotelPane(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CustomerSingleHotelView.fxml"));
            Pane singleHotel = (Pane) loader.load();
            hotelListScrollPane.getChildren().add(singleHotel);
            singleHotel.setLayoutX(5);
            singleHotel.setLayoutY(10);
            customerSingleHotelViewController = loader.getController();
            customerSingleHotelViewController.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
