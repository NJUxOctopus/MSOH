package ui.view.presentation.Marketer;

import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerSingleOrderController implements ControlledStage {

    StageController stageController;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 撤销按钮结果，显示确认撤销弹窗
     */
    public void confirmUndo(){

    }

    /**
     * 详情按钮结果，显示订单详情
     */
    public void showOrderDetails(){

    }
}
