package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkOrderDetailsController implements ControlledStage {

    StageController stageController;

    private static String resource = "clerk/ClerkOrderDetails.fxml";

    @FXML
    private Button backButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 后退按钮结果，显示上级订单列表界面
     */
    public void showOrderList() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

    /**
     * 入住按钮结果，显示确认入住弹窗
     */
    public void confirmCheckIn() {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkConfirmCheckIn.fxml", 1);
    }


}
