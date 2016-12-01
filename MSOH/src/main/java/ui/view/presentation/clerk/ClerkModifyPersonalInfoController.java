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
public class ClerkModifyPersonalInfoController implements ControlledStage {

    StageController stageController;

    private static String resource = "clerk/ClerkModifyPersonalInfo.fxml";

    @FXML
    private Button modifyPWButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 修改密码按钮结果，显示修改密码界面
     */
    public void showModifyPW() {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkModifyPassword.fxml", 1);
    }

    /**
     * 后退按钮结果，显示确认退出提示弹框
     */
    public void showConfirmExit() {
        stageController = new StageController();
        stageController.loadStage("clerk/ConfirmExit.fxml", 1);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed(resource);
    }

}
