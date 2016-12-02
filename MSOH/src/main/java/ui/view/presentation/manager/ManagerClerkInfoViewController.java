package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerClerkInfoViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String resource = "manager/ManagerClerkInfoView.fxml";

    @FXML
    private Button addButton;

    @FXML
    private Button modifyIconButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField customerNameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField cleckIDTextField;

    @FXML
    private TextField hotelIDTextField;

    @FXML
    private ImageView iconImage;

    @FXML
    private Pane addCleckPane;

    @FXML
    private Pane modifyCleckPane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 关闭当前界面
     */
    @FXML
    private void closeStage() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 0.75);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed(resource);
    }

    @FXML
    private void modifyIcon(){

    }

    @FXML
    private void confirmInfo(){
        if(addButton.getText().equals("添加"))
            System.out.print("1");
        if(addButton.getText().equals("修改"))
            System.out.print("2");
    }

    public void setModifyVer(){
        addButton.setText("修改");
        addCleckPane.setOpacity(0);
        modifyCleckPane.setOpacity(1);
    }

    public void setAddVer(){
        addButton.setText("添加");
        addCleckPane.setOpacity(1);
        modifyCleckPane.setOpacity(0);
    }

    public void init(){

    }
}
