package ui.view.presentation.marketer;

import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerModifyPersonalInfoController implements ControlledStage {

    StageController stageController;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 修改密码按钮结果，显示修改密码界面
     */
    public void showModifyPW() {
        stageController = new StageController();
        stageController.loadStage("marketer/MarketerModifyPassword.fxml", 1);
    }
}
