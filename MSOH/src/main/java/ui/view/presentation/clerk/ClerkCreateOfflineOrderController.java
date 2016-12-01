package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkCreateOfflineOrderController implements ControlledStage {

    StageController stageController;

    private static String resource = "clerk/ClerkCreateOfflineOrder.fxml";

    @FXML
    private TextField peopleNumTextField;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 减号按钮结果，房客人数减一
     */
    public void minusPeopleNum() {
        int peopleNum = Integer.parseInt(peopleNumTextField.getText());
        if (peopleNum != 1) {
            peopleNumTextField.setText(String.valueOf((peopleNum - 1)));
        }
    }

    /**
     * 加号按钮结果，房客人数加一
     */
    public void addPeopleNum() {
        int peopleNum = Integer.parseInt(peopleNumTextField.getText());
        peopleNumTextField.setText(String.valueOf((peopleNum + 1)));
    }

    /**
     * 后退按钮结果，显示确认退出提示弹框
     */
    public void showConfirmExit() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 1);
        ConfirmExitController controller = (ConfirmExitController)stageController.getController();
        controller.setToBeClosed(resource);
    }

}
