package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/12/1.
 */
public class ClerkHotelPromotionController implements ControlledStage {

    private StageController stageController;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 制定按钮结果，显示制定酒店促销策略界面
     */
    @FXML
    private void showCreatePromotion() {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkCreateHotelPromotion.fxml", 1);
    }

}
