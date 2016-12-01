package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerInfoViewController implements ControlledStage{
    private StageController stageController;

    private String resources = "customer/CustomerInfoView.fxml";

    private Stage stage;

    @FXML
    private ImageView background;

    @FXML
    private Button modifyPasswordButton;

    @FXML
    private Button saveInfoButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField creditTextField;

    @FXML
    private TextField memberTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private Button modifyIconButton;

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

    @FXML
    private void modifyIcon(){

    }

    @FXML
    private void modifyPassword(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerModifyPasswordView.fxml", 1);
    }

    @FXML
    private void modifyInfo(){

    }
}

