package ui.view.presentation.clerk;

import ui.view.presentation.util.ControlledStage;
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
     * 修改按钮结果，显示修改酒店促销策略界面
     */
    public void showModifyPromotion() {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkModifyPromotion.fxml", 1);
    }

    /**
     * 删除按钮结果，显示确认删除提示弹窗
     */
    public void deletePromotion(){

    }

}
