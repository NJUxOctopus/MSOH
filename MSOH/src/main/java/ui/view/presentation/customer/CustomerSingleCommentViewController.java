package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/30.
 */
public class CustomerSingleCommentViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Label customerNameLabel;

    private String customerName;

    @FXML
    private Label scoreLabel;

    private String score;

    @FXML
    private Label commentLabel;

    private String comment;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void init(){
        customerName = "111";
        score = "2";
        comment = "1111";
        customerNameLabel.setText(customerName);
        scoreLabel.setText(score);
        commentLabel.setText("\"" + comment + "\"");

    }

}