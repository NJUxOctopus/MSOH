package ui.view.presentation.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        stageController.closeStage(resources);
    }

    /**
     * 不选择日期，直接关闭窗口
     */
    @FXML
    private void cancel(){
        stageController.closeStage(resources);
    }

    public void init(){
        ObservableList<String> year = FXCollections.observableArrayList();
        year.add("2016");
        yearChoiceBox.setItems(year);

        monthChoiceBox.setItems(FXCollections.observableArrayList(
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"));

    }

}
