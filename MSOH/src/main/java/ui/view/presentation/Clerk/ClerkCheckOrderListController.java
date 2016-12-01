package ui.view.presentation.Clerk;

import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkCheckOrderListController implements ControlledStage {

    StageController stageController;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 线下入住按钮结果，显示线下入住界面
     */
    public void showCreateOfflineOrder() {
        stageController = new StageController();
        stageController.loadStage("Clerk/ClerkCreateOfflineOrder.fxml", 1);
    }


}
