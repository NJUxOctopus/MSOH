package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/28.
 */
public class ClerkModifyPasswordController implements ControlledStage {

    private StageController stageController;

    private static String resource = "clerk/ClerkModifyPassword.fxml";

    @FXML
    private Button backButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 后退按钮结果，显示提示确认退出弹窗
     */
    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 1);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed(resource);
    }
}
