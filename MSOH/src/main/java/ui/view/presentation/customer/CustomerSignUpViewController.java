package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/25.
 */
public class CustomerSignUpViewController implements ControlledStage{
    StageController stageController;

    private String resources = "customer/CustomerSignUpViewView.fxml";

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private Button upload;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField fisrtPasswordTextField;

    @FXML
    private TextField secondPasswordTextField;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }

    @FXML
    private void uploadIcon(){

    }

    @FXML
    private void signUp(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerSignUpSelectView.fxml");
    }

}
