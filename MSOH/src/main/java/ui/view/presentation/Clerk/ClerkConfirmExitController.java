package ui.view.presentation.Clerk;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/29.
 */
public class ClerkConfirmExitController implements ControlledStage {

    StageController stageController;

    private static String resource = "Clerk/ClerkConfirmExit.fxml";
    private static String toBeClosed = "";

    @FXML
    private Button exitButton;
    @FXML
    private Button cancelButton;

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
}
