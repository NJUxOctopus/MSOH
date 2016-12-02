package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerMarketerInfoViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String resources = "manager/ManagerMarketerInfoView.fxml";

    @FXML
    private Button addButton;

    @FXML
    private Button modifyIconButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField marketerNameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField marketerIDTextField;

    @FXML
    private Pane modifyMarketerPane;

    @FXML
    private Pane addMarketerPane;


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

    @FXML
    private void confirmInfo(){
        if(addButton.getText().equals("添加"))
            System.out.print("1");
        if(addButton.getText().equals("修改"))
            System.out.print("2");
    }

    public void setModifyVer(){
        addButton.setText("修改");
        addMarketerPane.setOpacity(0);
        modifyMarketerPane.setOpacity(1);
    }

    public void setAddVer(){
        addButton.setText("添加");
        addMarketerPane.setOpacity(1);
        modifyMarketerPane.setOpacity(0);
    }

    public void init(){

    }

}
