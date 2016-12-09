package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;

/**
 * Created by island on 2016/11/25.
 */
public class CustomerSignUpSelectViewController implements ControlledStage{
    StageController stageController;

    private String resource = "customer/CustomerSignUpSelectView.fxml";

    private String customerID;

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
        stageController.closeStage(resource);
    }

    @FXML
    private void signUpNormalMember(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerSignUpNormalMemberView.fxml", 1);
        CustomerSignUpNormalMemberViewController customerSignUpNormalMemberViewController = (CustomerSignUpNormalMemberViewController) stageController.getController();
        customerSignUpNormalMemberViewController.init(customerID);
        closeStage();
    }

    @FXML
    private void signUpEnterpriseMember(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerSignUpEnterpriseMemberView.fxml", 1);
        CustomerSignUpEnterpriseMemberViewController customerSignUpEnterpriseMemberViewController = (CustomerSignUpEnterpriseMemberViewController) stageController.getController();
        customerSignUpEnterpriseMemberViewController.init(customerID);
        closeStage();
    }

    public void init(String customerID){
        this.customerID = customerID;
    }
}
