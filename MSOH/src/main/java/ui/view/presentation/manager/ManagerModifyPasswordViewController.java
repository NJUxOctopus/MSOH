package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import ui.controller.ManagerInfoChangeController;
import ui.view.controllerservice.ManagerInfoChange;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerModifyPasswordViewController implements ControlledStage {
    StageController stageController;

    private String resource = "manager/ManagerModifyPasswordView.fxml";

    private String managerID;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private Button confirmButton;

    @FXML
    private PasswordField originalPasswordTextField;

    @FXML
    private PasswordField firstNewPasswordTextField;

    @FXML       
    private PasswordField secondNewPasswordTextField;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

    /**
     * 修改密码方法
     */
    @FXML
    private void confirmModifyPassword(){
        //获得输入信息
        String originalPassword = originalPasswordTextField.getText();
        String firstNewPassword = firstNewPasswordTextField.getText();
        String secondNewPassword = secondNewPasswordTextField.getText();
        //调用修改密码方法
        ManagerInfoChange managerInfoChange = new ManagerInfoChangeController();
        try {
            ResultMessage resultMessage = managerInfoChange.changePassword(managerID, originalPassword, firstNewPassword, secondNewPassword);
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            if(resultMessage ==ResultMessage.Blank){
                errorBoxController.setLabel("未完成填写！");
            }
            else if(resultMessage == ResultMessage.ChangePasswordWrongOldPw){
                errorBoxController.setLabel("原密码输入错误！");
                clearField();
            }
            else if(resultMessage == ResultMessage.ChangePassword2DifferentNew){
                errorBoxController.setLabel("两次密码输入不一致！");
                clearField();
            }
            else if(resultMessage == ResultMessage.ChangePasswordSuccess){
                errorBoxController.setLabel("成功修改密码！");
                stageController = new StageController();
                stageController.closeStage(resource);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 清空输入区域
     */
    private void clearField(){
        originalPasswordTextField.setText("");
        firstNewPasswordTextField.setText("");
        secondNewPasswordTextField.setText("");
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }

    public void init(String managerID){
        this.managerID = managerID;
    }
}
