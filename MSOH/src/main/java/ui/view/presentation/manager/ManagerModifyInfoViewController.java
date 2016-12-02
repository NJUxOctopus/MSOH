package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerModifyInfoViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String resources = "manager/ManagerModifyInfoView.fxml";

    @FXML
    private Button saveInfoButton;

    @FXML
    private Button backButton;

    @FXML
    private Button modifyPasswordButton;

    @FXML
    private Button modifyIconButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField idTextField;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 关闭当前界面
     */
    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }

    @FXML
    private void modifyIcon(){

    }

    /**
     * 点击修改密码按钮，跳转至密码修改界面
     */
    @FXML
    private void modifyPassword(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerModifyPasswordView.fxml", 1);
    }

    @FXML
    private void saveInfo(){

    }

    public void init(){
    }
}
