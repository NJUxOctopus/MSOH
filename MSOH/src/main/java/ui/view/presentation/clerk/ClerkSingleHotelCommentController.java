package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import vo.CommentVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/9.
 */
public class ClerkSingleHotelCommentController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Label customerNameLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label commentTimeLabel;
    @FXML
    private TextArea commentTextArea;


    private CommentVO commentVO;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(CommentVO commentVO) throws RemoteException {
        this.commentVO = commentVO;
        customerNameLabel.setText(commentVO.customerName);
        scoreLabel.setText(String.valueOf(commentVO.score));
        commentTimeLabel.setText(commentVO.commentTime.toString());
        commentTextArea.setText(commentVO.comment);
    }
}
