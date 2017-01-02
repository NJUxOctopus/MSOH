package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.controller.ClerkInfoChangeController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.ClerkInfoChange;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;
import vo.ClerkVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/11/28.
 */
public class ClerkModifyPersonalInfoController implements ControlledStage {

    private StageController stageController;

    private static String resource = "clerk/ClerkModifyPersonalInfo.fxml";

    @FXML
    private Label clerkName;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label idTextField;
    @FXML
    private TextField phoneTextField;


    private String clerkID;
    private ClerkVO clerkVO;
    private UserAdmin userAdmin;
    private ClerkInfoChange clerkInfoChange;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String clerkID) throws RemoteException {
        //初始化工作人员姓名、密码、身份证号、手机号信息
        this.clerkID = clerkID;
        userAdmin = new UserAdminController();
        clerkVO = userAdmin.findClerkByID(this.clerkID);
        clerkName.setText(clerkVO.name);
        nameTextField.setText(clerkVO.name);
        passwordTextField.setText(clerkVO.password);
        idTextField.setText(this.clerkID);
        phoneTextField.setText(clerkVO.phone);
    }

    /**
     * 修改密码按钮结果，显示修改密码界面
     */
    @FXML
    private void showModifyPW() {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkModifyPassword.fxml", 1);
        ClerkModifyPasswordController clerkModifyPasswordController = (ClerkModifyPasswordController) stageController.getController();
        clerkModifyPasswordController.initial(clerkID);
    }

    /**
     * 后退按钮结果，显示确认退出提示弹框
     */
    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        if (!isModified()) {
            //未修改任何信息
            stageController.closeStage(resource);
        } else {
            stageController.loadStage("util/ConfirmExit.fxml", 0.8);
            ConfirmExitController controller = (ConfirmExitController) stageController.getController();
            controller.setToBeClosed(resource);
        }

    }

    /**
     * 确认修改按钮结果，修改信息
     */
    @FXML
    private void confirmModify() throws RemoteException {
        if (!isModified()) {
            //未修改任何信息
            this.returnMessage("信息未修改！");
        } else {
            String name = nameTextField.getText();
            String phone = phoneTextField.getText();
            ClerkVO clerkVO = new ClerkVO(name, phone, clerkID);
            clerkInfoChange = new ClerkInfoChangeController();
            ResultMessage resultMessage = clerkInfoChange.changeInfo(clerkVO);
            if (resultMessage.equals(ResultMessage.Blank)) {
                this.returnMessage("信息填写不完整！");
            } else if (resultMessage.equals(ResultMessage.DataFormatWrong)) {
                this.returnMessage("手机号格式错误！");
            } else if (resultMessage.equals(ResultMessage.ChangeInfoSuccess)) {
                stageController = this.returnMessage("修改成功！");
                ClerkFrameController clerkFrameController = (ClerkFrameController) stageController.getController("clerk/ClerkFrame.fxml");
                clerkFrameController.initial(clerkID);
                stageController.closeStage(resource);
            } else {
                this.returnMessage("未知错误！");
            }
        }

    }

    /**
     * 返回信息是否被修改
     *
     * @return
     */
    private boolean isModified() {
        if (nameTextField.getText().equals(clerkVO.name) && passwordTextField.getText().equals(clerkVO.password)
                && phoneTextField.getText().equals(clerkVO.phone)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 返回修改结果
     *
     * @return
     */
    private StageController returnMessage(String error) {
        stageController = new StageController();
        stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
        ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
        errorBoxController.setLabel(error);
        return stageController;
    }

    /**
     * 退出按钮结果，退出程序
     */
    @FXML
    private void exit() {
        System.exit(0);
    }

}
