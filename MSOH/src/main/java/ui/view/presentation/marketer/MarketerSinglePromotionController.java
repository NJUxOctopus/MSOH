package ui.view.presentation.marketer;

import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerSinglePromotionController implements ControlledStage {

    StageController stageController;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 修改按钮结果，显示修改网站促销策略界面
     */
    public void showModifyPromotion() {
        stageController = new StageController();
        stageController.loadStage("marketer/MarketerModifyPromotion.fxml", 1);
    }

    /**
     * 删除按钮结果，显示确认删除弹窗
     */
    public void deletePromotion() {

    }

}
