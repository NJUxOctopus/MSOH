package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerModifyCustomerInfoViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String resources = "manager/ManagerModifyCustomerInfoView.fxml";

    @FXML
    private Button saveInfoButton;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox typeChoiceBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField creditTextField;

    @FXML
    private TextField memberTextField;

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
    private void modifyInfo(){

    }

    public void init(){
    }
}
