package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerSinglePersonViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Label typeLabel;

    private String type = "";

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Button modifyButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 判断人员类型，打开对应修改信息界面
     */
    @FXML
    private void showModifyView(){
        type = typeLabel.getText();
        //type = "酒店工作人员";
        if(type.equals("酒店工作人员"))
            showModifyClerkView();
        if(type.equals("客户"))
            showModifyCustomerView();
        if(type.equals("网站营销人员"))
            showModifyMarketerView();
    }

    private void showModifyClerkView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerClerkInfoView.fxml", 1);
        ManagerClerkInfoViewController managerClerkInfoViewController = (ManagerClerkInfoViewController) stageController.getController();
        managerClerkInfoViewController.setModifyVer();
        managerClerkInfoViewController.init();
    }

    private void showModifyCustomerView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerModifyCustomerInfoView.fxml", 1);
        ManagerModifyCustomerInfoViewController managerModifyCustomerInfoViewController = (ManagerModifyCustomerInfoViewController) stageController.getController();
        managerModifyCustomerInfoViewController.init();
    }

    private void showModifyMarketerView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerMarketerInfoView.fxml", 1);
        ManagerMarketerInfoViewController managerMarketerInfoViewController = (ManagerMarketerInfoViewController) stageController.getController();
        managerMarketerInfoViewController.setModifyVer();
        managerMarketerInfoViewController.init();
    }

    public void init(){

    }
}
