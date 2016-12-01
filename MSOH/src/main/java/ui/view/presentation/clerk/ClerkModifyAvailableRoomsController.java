package ui.view.presentation.clerk;

import javafx.fxml.FXMLLoader;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/29.
 */
public class ClerkModifyAvailableRoomsController implements ControlledStage {

    StageController stageController;

    private static String resource = "clerk/ClerkModifyAvailableRooms.fxml";

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 后退按钮结果，显示确认退出弹窗
     */
    public void showConfirmExit() {
        stageController = new StageController();
        stageController.loadStage("clerk/ConfirmExit.fxml", 1);
        FXMLLoader loader = stageController.getLoader();
        ConfirmExitController controller = loader.getController();
        controller.setToBeClosed(resource);
    }
}
