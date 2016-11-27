package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.view.presentation.StageController;
import ui.view.presentation.ControlledStage;

/**
 * Created by island on 2016/11/25.
 */
public class CustomerSignUpSelectViewController implements ControlledStage{
    StageController stageController;

    private String resources = "customer/CustomerSignUpSelectView.fxml";

    @FXML
    private Button cancelButton;

    @FXML
    private Button normalMemberButton;

    @FXML
    private Button enterpriseMemberButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController = new StageController();
        stageController.closeStage(resources);
    }

    @FXML
    private void cancelOrder(){

    }

    @FXML
    private void signUpNormalMember(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerSignUpNormalMemberView.fxml", 1);
        closeStage();
    }

    @FXML
    private void signUpEnterpriseMember(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerSignUpEnterpriseMemberView.fxml", 1);
        closeStage();
    }
}
