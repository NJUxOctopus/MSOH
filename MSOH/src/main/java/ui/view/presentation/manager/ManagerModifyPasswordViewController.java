package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerModifyPasswordViewController implements ControlledStage {
    StageController stageController;

    private String resource = "manager/ManagerModifyPasswordView.fxml";

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField originalPasswordTextField;

    @FXML
    private TextField firstNewPasswordTextField;

    @FXML
    private TextField SecondNewPasswordTextField;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 0.75);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed(resource);
    }

    @FXML
    private void confirmModifyPassword(){

    }
}
