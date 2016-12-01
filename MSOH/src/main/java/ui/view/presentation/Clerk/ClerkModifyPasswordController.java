package ui.view.presentation.Clerk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/28.
 */
public class ClerkModifyPasswordController implements ControlledStage {

    StageController stageController;

    private static String resource = "Clerk/ClerkModifyPassword.fxml";

    @FXML
    private Button backButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 后退按钮结果，显示提示确认退出弹窗
     */
    public void showConfirmExit() {
        stageController = new StageController();
        stageController.loadStage("Clerk/ClerkConfirmExit.fxml", 1);
        FXMLLoader loader = stageController.getLoader();
        ClerkConfirmExitController controller = loader.getController();
        controller.setToBeClosed(resource);
    }
}
