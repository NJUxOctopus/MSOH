package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkSingleOrderController implements ControlledStage {

    private StageController stageController;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 详情按钮结果，显示订单详情界面
     */
    @FXML
    private void showOrderDetails() {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkOrderDetails.fxml", 1);
    }

    /**
     * 入住按钮结果，把订单转换成已入住订单
     */
    @FXML
    private void confirmCheckIn() {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkConfirmCheckIn.fxml", 1);
    }


}
