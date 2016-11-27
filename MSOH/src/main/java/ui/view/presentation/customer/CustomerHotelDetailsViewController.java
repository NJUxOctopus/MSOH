package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerHotelDetailsViewController implements ControlledStage{
    StageController stageController;

    private String resources = "customer/CustomerHotelDetailsView.fxml";

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }
}
