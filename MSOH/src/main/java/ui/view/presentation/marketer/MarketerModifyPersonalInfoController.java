package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.controller.UserAdminController;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.MarketerVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerModifyPersonalInfoController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Label marketerName;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label idTextField;
    @FXML
    private TextField phoneTextField;

    private String resource = "marketer/MarketerModifyPersonalInfo.fxml";

    private String marketerID;
    private MarketerVO marketerVO;
    private UserAdmin userAdmin;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String ID) throws RemoteException {
        userAdmin = new UserAdminController();
        marketerID = ID;
        marketerVO = userAdmin.findMarketerByID(marketerID);
        marketerName.setText(marketerVO.name);
        nameTextField.setText(marketerVO.name);
        passwordTextField.setText(marketerVO.password);
        idTextField.setText(marketerVO.ID);
        phoneTextField.setText(marketerVO.phone);
    }

    /**
     * 修改密码按钮结果，显示修改密码界面
     */
    @FXML
    private void showModifyPW() {
        stageController = new StageController();
        stageController.loadStage("marketer/MarketerModifyPassword.fxml", 1);
        MarketerModifyPasswordController marketerModifyPasswordController = (MarketerModifyPasswordController) stageController.getController();
        marketerModifyPasswordController.initial(marketerID);
    }

    /**
     * 后退按钮结果，显示确认退出提示弹窗
     */
    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        if (!this.isModified()) {
            //未修改信息
            stageController.closeStage(resource);
        } else {
            stageController.loadStage("util/ConfirmExit.fxml", 0.8);
            ConfirmExitController controller = (ConfirmExitController) stageController.getController();
            controller.setToBeClosed(resource);
        }
    }

    /**
     * 判断是否修改了信息
     *
     * @return
     */
    private boolean isModified() {
        if (nameTextField.getText().equals(marketerVO.name) && passwordTextField.getText().equals(marketerVO.password)
                && phoneTextField.getText().equals(marketerVO.phone)) {
            //未修改信息
            return false;
        } else {
            return true;
        }
    }
}
