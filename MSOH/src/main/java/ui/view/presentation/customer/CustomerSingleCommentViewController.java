package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.CommentVO;

/**
 * Created by island on 2016/11/30.
 */
public class CustomerSingleCommentViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Label customerNameLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label commentLabel;

    @FXML
    private Label timeLabel;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void init(CommentVO commentVO){

        customerNameLabel.setText(commentVO.customerName);
        scoreLabel.setText(commentVO.score + "");
        commentLabel.setText("\"" + commentVO.comment + "\"");
        timeLabel.setText(commentVO.commentTime.toString());

    }

}