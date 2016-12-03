package ui.view.presentation.util;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.view.presentation.StageController;
import util.ResultMessage;

/**
 * Created by ST on 2016/12/2.
 */
public class ErrorBoxController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Label errorTypeLabel;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void setLabel(String error) {
        errorTypeLabel.setText(error);
    }

    /**
     * 取消按钮结果，关闭弹窗
     */
    @FXML
    private void cancel() {
        stageController = new StageController();
        stageController.closeStage("util/ErrorBoxView.fxml");
    }

}
