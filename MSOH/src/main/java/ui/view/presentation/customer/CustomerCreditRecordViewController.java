package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/23.
 */
public class CustomerCreditRecordViewController implements ControlledStage {
    StageController stageController;

    private String resources = "customer/CustomerCreditRecordView.fxml";

    @FXML
    private ImageView background;

    @FXML
    private Label creditLabel;

    @FXML
    private Button nextPageButton;

    @FXML
    private Button lastPageButton;

    @FXML
    private ChoiceBox selectPageBox;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }

    @FXML
    private void nextPage(){

    }

    @FXML
    private void lastPage(){

    }

    @FXML
    private void selectPage(){

    }
}
