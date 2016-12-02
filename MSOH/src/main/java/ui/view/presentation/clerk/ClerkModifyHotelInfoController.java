package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkModifyHotelInfoController implements ControlledStage {

    private StageController stageController;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 1);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed("clerk/ClerkModifyHotelInfo.fxml");
    }

}
