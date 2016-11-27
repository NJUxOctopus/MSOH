package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerSignUpEnterpriseMemberViewController implements ControlledStage {
    StageController stageController;

    private String resources = "customer/CustomerSignUpEnterpriseMemberView.fxml";

    @FXML
    private Button backButton;

    @FXML
    private TextField corporateNameTextField;

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
    private void signUpCorporate(){

    }

}
