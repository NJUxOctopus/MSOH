package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ST on 2016/12/1.
 */
public class ClerkConfirmCheckController implements ControlledStage {

    private StageController stageController;

    private static String resource = "clerk/ClerkConfirmCheckIn.fxml";

    @FXML
    private Label cueLabel;

    //获取当前时间
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private Timestamp time = Timestamp.valueOf(dateFormat.format(date));

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }


    /**
     * 初始化方法，初始界面
     *
     * @param status
     */
    public void initial(String status) {
        if (status.equals("checkIn")) {
            cueLabel.setText("确认入住？");
        } else if (status.equals("checkOut")) {
            cueLabel.setText("确认退房？");
        }
    }

    /**
     * 确认按钮结果，确认入住或退房，记录入住或退房时间
     */
    @FXML
    private void confirmCheck() {

    }

    /**
     * 取消按钮结果，取消操作，关闭弹窗
     */
    @FXML
    private void cancelCheck() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

}
