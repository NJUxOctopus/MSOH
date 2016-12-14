package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ui.controller.CustomerInfoChangeController;
import ui.controller.ManagerInfoChangeController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.CustomerInfoChange;
import ui.view.controllerservice.ManagerInfoChange;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;
import vo.CustomerVO;
import vo.ManagerVO;

import java.awt.*;
import java.rmi.RemoteException;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerModifyInfoViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String resource = "manager/ManagerModifyInfoView.fxml";

    private String managerID;

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

    private String name = "";

    private String phone = "";

    private String id = "";

    private String newName = "";

    private String newPhone = "";

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 关闭当前界面
     */
    @FXML
    private void closeStage() {
        getNewInfo();
        if(!newPhone.equals(phone) || !newName.equals(name)){
            stageController = new StageController();
            stageController.loadStage("util/ConfirmExit.fxml", 0.75);
            ConfirmExitController controller = (ConfirmExitController) stageController.getController();
            controller.setToBeClosed(resource);

        }else {
            stageController = new StageController();
            stageController.closeStage(resource);
        }
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
        ManagerModifyPasswordViewController managerModifyPasswordViewController = (ManagerModifyPasswordViewController) stageController.getController();
        managerModifyPasswordViewController.init(managerID);
    }

    @FXML
    private void saveInfo(){
        getNewInfo();
        name = newName;
        phone = newPhone;
        ManagerInfoChange managerInfoChange = new ManagerInfoChangeController();
        try {
            ResultMessage resultMessage = managerInfoChange.changeInfo(new ManagerVO(name, phone, "", managerID, ""));
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            if(resultMessage == ResultMessage.Blank){
                errorBoxController.setLabel("请填写完整信息！");
            }
            else if(resultMessage == ResultMessage.phoneFormatWrong){
                errorBoxController.setLabel("手机格式错误！");
            }
            else if(resultMessage == ResultMessage.ChangeInfoSuccess){
                errorBoxController.setLabel("成功修改信息！");
                stageController = new StageController();
                stageController.closeStage(resource);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 网管修改信息界面初始化方法
     * @param managerID
     */
    public void init(String managerID){
        this.managerID = managerID;
        UserAdmin userAdmin = new UserAdminController();
        try {
            ManagerVO managerVO = userAdmin.findManagerByID(managerID);
            name = managerVO.name;
            phone = managerVO.phone;
            id = managerVO.ID;
            nameTextField.setText(name);
            phoneTextField.setText(phone);
            idTextField.setText(id);
        }catch (RemoteException e){
            e.printStackTrace();
        }
        getNewInfo();
    }

    /**
     * 获得当前面板上的信息
     */
    private void getNewInfo(){
        newName = nameTextField.getText();
        newPhone = phoneTextField.getText();
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
