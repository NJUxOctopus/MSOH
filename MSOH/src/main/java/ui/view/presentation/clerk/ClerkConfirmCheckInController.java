package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/12/1.
 */
public class ClerkConfirmCheckInController implements ControlledStage {

    private StageController stageController;

    private static String resource = "clerk/ClerkConfirmCheckIn.fxml";

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 确认按钮结果，确认入住，记录入住时间
     */
    @FXML
    private void confirmCheckIn() {

    }

    /**
     * 取消按钮结果，取消操作，关闭弹窗
     */
    @FXML
    private void cancelCheckIn() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

}
