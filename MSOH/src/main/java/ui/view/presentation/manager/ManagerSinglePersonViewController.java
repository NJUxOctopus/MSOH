package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.controller.UserAdminController;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import vo.ClerkVO;
import vo.CustomerVO;
import vo.MarketerVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerSinglePersonViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String id;

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
        if(type.equals("酒店工作人员"))
            showModifyClerkView();
        if(type.equals("客户"))
            showModifyCustomerView();
        if(type.equals("网站营销人员"))
            showModifyMarketerView();
    }

    /**
     * 显示修改酒店工作人员信息界面
     */
    private void showModifyClerkView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerClerkInfoView.fxml", 1);
        ManagerClerkInfoViewController managerClerkInfoViewController = (ManagerClerkInfoViewController) stageController.getController();
        managerClerkInfoViewController.setModifyVer(id);
    }

    /**
     * 显示修改客户信息界面
     */
    private void showModifyCustomerView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerModifyCustomerInfoView.fxml", 1);
        ManagerModifyCustomerInfoViewController managerModifyCustomerInfoViewController = (ManagerModifyCustomerInfoViewController) stageController.getController();
        managerModifyCustomerInfoViewController.init(id);
    }

    /**
     * 显示修改网站营销人员信息界面
     */
    private void showModifyMarketerView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerMarketerInfoView.fxml", 1);
        ManagerMarketerInfoViewController managerMarketerInfoViewController = (ManagerMarketerInfoViewController) stageController.getController();
        managerMarketerInfoViewController.setModifyVer(id);
    }

    /**
     * 根据人员类型和i，初始化单个人员面板
     * @param type
     * @param id
     */
    public void init(String type, String id){
        this.id = id;
        try {
            UserAdmin userAdmin = new UserAdminController();
            if (type.equals("客户")) {
                typeLabel.setText("客户");
                CustomerVO user = userAdmin.findCustomerByID(id);
                nameLabel.setText(user.name);
                phoneLabel.setText(user.phone);
                idLabel.setText(user.ID);
            }
            if (type.equals("网站营销人员")) {
                typeLabel.setText("网站营销人员");
                MarketerVO user = userAdmin.findMarketerByID(id);
                nameLabel.setText(user.name);
                phoneLabel.setText(user.phone);
                idLabel.setText(user.ID);
            }
            if (type.equals("酒店工作人员")) {
                typeLabel.setText("酒店工作人员");
                ClerkVO user = userAdmin.findClerkByID(id);
                nameLabel.setText(user.name);
                phoneLabel.setText(user.phone);
                idLabel.setText(user.ID);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
