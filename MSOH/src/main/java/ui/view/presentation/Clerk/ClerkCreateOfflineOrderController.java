package ui.view.presentation.Clerk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkCreateOfflineOrderController implements ControlledStage {

    StageController stageController;

    private static String resource = "Clerk/ClerkCreateOfflineOrder.fxml";

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
        stageController.loadStage("Clerk/ClerkConfirmExit.fxml", 1);
        FXMLLoader loader = stageController.getLoader();
        ClerkConfirmExitController controller = loader.getController();
        controller.setToBeClosed(resource);
    }

}
