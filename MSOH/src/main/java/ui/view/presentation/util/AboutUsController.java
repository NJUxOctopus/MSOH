package ui.view.presentation.util;

import javafx.fxml.FXML;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/12/21.
 */
public class AboutUsController implements ControlledStage {

    private StageController stageController;

    private String resource = "util/AboutUs.fxml";

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 确定按钮结果，关闭界面
     */
    @FXML
    private void cancel() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

}
