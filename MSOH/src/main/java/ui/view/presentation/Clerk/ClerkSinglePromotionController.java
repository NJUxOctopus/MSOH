package ui.view.presentation.Clerk;

import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkSinglePromotionController implements ControlledStage {

    StageController stageController;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 制定/修改按钮结果，显示制定酒店促销策略界面
     */
    public void showCreatePromotion() {
        stageController = new StageController();
        stageController.loadStage("Clerk/ClerkCreateHotelPromotion.fxml", 1);
    }

    /**
     * 删除按钮结果，显示确认删除提示弹窗
     */
    public void deletePromotion(){

    }

}
