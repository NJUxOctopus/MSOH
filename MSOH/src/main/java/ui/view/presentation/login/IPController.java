package ui.view.presentation.login;

import init.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/23.
 */
public class IPController implements ControlledStage {

    private StageController stageController;

    @FXML
    private TextField ipTextField;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 确定按钮结果，确定IP地址，进入登录界面
     */
    @FXML
    private void confirmAddress() throws RemoteException {
        LoginView loginView = new LoginView();
        loginView.setIPAddress(ipTextField.getText());
        loginView.linkServer();
        initLogin();
    }

    private void initLogin() throws RemoteException {
        stageController = new StageController();
        stageController.closeStage("login/IP.fxml");
        stageController.loadStage("login/LoginView.fxml", 1);
        LoginViewController loginViewController = (LoginViewController) stageController.getController();
        loginViewController.initial();
    }


    /**
     * 退出系统方法
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
