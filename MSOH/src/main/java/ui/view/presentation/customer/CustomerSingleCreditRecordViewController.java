package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/30.
 */
public class CustomerSingleCreditRecordViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Label timeLabel;

    @FXML
    private Label actionLabel;

    @FXML
    private Label orderLabel;

    @FXML
    private Label changeNumberLabel;

    @FXML
    private Label creditResultLabel;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void init(){
        timeLabel.setText("");
        actionLabel.setText("");
        orderLabel.setText("");
        changeNumberLabel.setText("");
        creditResultLabel.setText("");

    }

}
