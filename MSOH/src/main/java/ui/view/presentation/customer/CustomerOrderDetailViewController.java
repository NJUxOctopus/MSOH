package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerOrderDetailViewController implements ControlledStage{
    StageController stageController;

    private String resources = "customer/CustomerOrderDetailView.fxml";

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private Button cancelOrderButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }

    @FXML
    private void cancelOrder(){

    }
}
