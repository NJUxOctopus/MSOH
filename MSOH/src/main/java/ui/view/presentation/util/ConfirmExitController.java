package ui.view.presentation.util;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/29.
 */
public class ConfirmExitController implements ControlledStage {

    StageController stageController;

    private static String resource = "util/ConfirmExit.fxml";
    private static String toBeClosed = "";

    @FXML
    private Button exitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label label;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * set方法，设置要被关闭的界面
     */
    public void setToBeClosed(String theStage){
        toBeClosed = theStage;
    }

    /**
     * 确认退出按钮结果，显示上级修改个人信息界面
     */
    public void confirmExit() {
        stageController = new StageController();
        stageController.closeStage(resource);
        stageController.closeStage(toBeClosed);
    }

    /**
     * 取消退出按钮结果，关闭弹窗
     */
    public void cancelExit(){
        stageController = new StageController();
        stageController.closeStage(resource);
    }

    public void setLabel(String s){
        label.setText(s);
    }
}
