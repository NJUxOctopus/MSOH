package ui.view.presentation.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import ui.controller.LogInController;
import ui.view.controllerservice.LogIn;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/11/23.
 */
public class LoginViewController implements ControlledStage {

    private StageController stageController;

    private static String resource = "login/LoginView.fxml";

    @FXML
    private Pane pane;
    @FXML
    private Button loginButton;
    @FXML
    private Button rememberPWButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button skipButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField userNameField;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

//    /**
//     * Initializes the controller class. This method is automatically called
//     * after the fxml file has been loaded.
//     */
//    @FXML
//    private void initialize() {
//
//    }

//    /**
//     * Is called by the main application to give a reference back to itself.
//     *
//     * @param loginView
//     */
//    public void setLoginView(LoginView loginView) {
//
//        this.loginView = loginView;
//
//    }

    /**
     * 登录按钮结果，显示登录界面
     */
    @FXML
    private void handleLogin() throws RemoteException {
        LogIn logIn = new LogInController();
        String password = passwordField.getText();
        String userID = userNameField.getText();
        ResultMessage result = logIn.login(userID, password);
        stageController = new StageController();
        if (result.equals(ResultMessage.Blank)) {
            // 账号、密码为空
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setErrorType(result);
            controller.setLabel("账号或密码不能为空！");
        } else if (result.equals(ResultMessage.Login_NoUser)) {
            // 不存在该用户
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setErrorType(result);
            controller.setLabel("不存在对应用户！");
        } else if (result.equals(ResultMessage.Login_ClerkSuccess)) {
            // 酒店工作人员登录成功
            stageController.loadStage("clerk/ClerkFrame.fxml", 1);
            stageController.closeStage(resource);
        } else if (result.equals(ResultMessage.Login_ManagerSuccess)) {
            // 网站管理人员登录成功
            stageController.loadStage("manager/ManagerMainView.fxml", 1);
            stageController.closeStage(resource);
        } else if (result.equals(ResultMessage.Login_MarketerSuccess)) {
            // 网站营销人员登录成功
            stageController.loadStage("marketer/MarketerFrame.fxml", 1);
            stageController.closeStage(resource);
//        } else if (result.equals(ResultMessage.Login_CustomerSuccess)) {
        } else if (userID.equals("Admin") && password.equals("123456")) {
            // 客户登录成功
            stageController.loadStage("customer/CustomerMainView.fxml", 1);
            stageController.closeStage(resource);
        } else {
            // 密码错误
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setErrorType(result);
            controller.setLabel("密码错误！");
        }
    }

    /**
     * 注册按钮结果，显示客户注册界面
     */
    @FXML
    private void showRegister() {
        stageController = new StageController();
        stageController.loadStage("customer/CustomerSignUpView.fxml", 1);
    }

    /**
     * 跳过按钮结果，显示客户主界面
     */
    @FXML
    private void handleSkip() {
        stageController = new StageController();
        stageController.loadStage("customer/CustomerMainView.fxml", 1);
        stageController.closeStage(resource);
    }

    /**
     * 记住密码按钮结果，执行记住密码操作
     */
    @FXML
    private void rememberPW() {

    }
}
