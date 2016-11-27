package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerSignUpNormalMemberViewController implements ControlledStage {
    StageController stageController;

    private String resources = "customer/CustomerSignUpNormalMemberView.fxml";

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox yearChoiceBox;

    @FXML
    private ChoiceBox monthChoiceBox;

    @FXML
    private ChoiceBox dayChoiceBox;

    @FXML
    private Button confirmButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }

    @FXML
    private void signUpNormal(){

    }
}
