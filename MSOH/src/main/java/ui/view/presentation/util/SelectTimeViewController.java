package ui.view.presentation.util;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/12/1.
 */
public class SelectTimeViewController implements ControlledStage {
    StageController stageController;

    private String resources = "util/SelectTimeView.fxml";

    @FXML
    private ChoiceBox yearChoiceBox;

    @FXML
    private ChoiceBox monthChoiceBox;

    @FXML
    private ChoiceBox dayChoiceBox;

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 保存当前选择的日期，关闭窗口
     */
    @FXML
    private void confirm(){
        //关闭当前窗口
        stageController.closeStage(resources);
    }

    /**
     * 不选择日期，直接关闭窗口
     */
    @FXML
    private void cancel(){
        stageController.closeStage(resources);
    }
}
