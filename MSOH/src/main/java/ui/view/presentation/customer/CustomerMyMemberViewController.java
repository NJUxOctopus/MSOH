package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerMyMemberViewController implements ControlledStage{
    StageController stageController;

    private String resource = "customer/CustomerMyMemberView.fxml";

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private Label typeOfMemberLabel;

    @FXML
    private Label gradeOfMemberLabel;

    @FXML
    private Label discountOfMemberLabel;

    @FXML
    private Button signUpButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resource);
    }

    @FXML
    private void showSignUpSelectView(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerSignUpSelectView.fxml", 0.5);
    }

}
