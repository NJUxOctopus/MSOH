package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerCreateWebPromotionController implements ControlledStage {

    private StageController stageController;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 开始时间按钮结果，显示选择开始时间弹窗
     */
    @FXML
    private void showBeginTime() {
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml", 0.8);
    }

    /**
     * 结束时间按钮结果，显示选择结束时间弹窗
     */
    @FXML
    private void showEndTime() {
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml", 0.8);
    }

    /**
     * 后退按钮结果，显示确认退出提示弹窗
     */
    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 0.8);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed("marketer/MarketerModifyHotelPromotion.fxml");
    }
}
