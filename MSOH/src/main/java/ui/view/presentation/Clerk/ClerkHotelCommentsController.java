package ui.view.presentation.Clerk;

import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/29.
 */
public class ClerkHotelCommentsController implements ControlledStage {

    StageController stageController;

    private static String resource = "Clerk/ClerkHotelComments.fxml";

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 后退按钮结果，显示上级酒店信息页面
     */
    public void showConfirmExit() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }
}
