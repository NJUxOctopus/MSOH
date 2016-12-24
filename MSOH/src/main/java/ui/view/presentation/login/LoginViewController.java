package ui.view.presentation.login;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import ui.controller.LogInController;
import ui.view.controllerservice.LogIn;
import ui.view.presentation.StageController;
import ui.view.presentation.clerk.ClerkFrameController;
import ui.view.presentation.customer.CustomerMainView;
import ui.view.presentation.customer.CustomerMainViewController;
import ui.view.presentation.manager.ManagerMainView;
import ui.view.presentation.manager.ManagerMainViewController;
import ui.view.presentation.marketer.MarketerFrameController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by ST on 2016/11/23.
 */
public class LoginViewController implements ControlledStage {

    private StageController stageController;

    private static String resource = "login/LoginView.fxml";

    @FXML
    private CheckBox rememberPW;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField userNameField;
    @FXML
    private Button loginButton;
    @FXML
    private ChoiceBox accountChoiceBox;

    private LogIn logIn;

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
     * 初始化登录界面，监听键盘响应事件
     */
    public void initial() throws RemoteException {
        logIn = new LogInController();
        ObservableList<String> accounts = FXCollections.observableArrayList();
        List<String> toAdd = logIn.getRememberedID();

        for (int i = 0; i < toAdd.size(); i++) {
            accounts.add(toAdd.get(i));
        }

        accountChoiceBox.setItems(accounts);

        if (!accounts.isEmpty()) {
            userNameField.setText(accounts.get(0));
        }

        accountChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String selectedAccount = (String) accountChoiceBox.getSelectionModel().getSelectedItem();
                userNameField.setText(selectedAccount);
            }
        });

        passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    try {
                        handleLogin();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 密码文字框监听，判断对应账号是否记住密码，如果记住则直接显示
     */
    @FXML
    private void autoPW() throws RemoteException {
        logIn = new LogInController();
        String pw = logIn.ifRememberPW(userNameField.getText());
//        System.out.println("Login: " + pw);
        passwordField.setText(pw);
        if (!pw.equals("")) {
            //如果记住密码，勾选记住密码选项
            rememberPW.setSelected(true);
        }
    }

    /**
     * 登录按钮结果，进行登录操作
     */
    @FXML
    private void handleLogin() throws RemoteException {
        logIn = new LogInController();
        String password = passwordField.getText();
        String userID = userNameField.getText();
        ResultMessage loginResult = logIn.login(userID, password);
        stageController = new StageController();
        boolean rememberPWSelected = rememberPW.isSelected();

        if (loginResult.equals(ResultMessage.Blank)) {
            // 账号、密码为空
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setLabel("账号或密码不能为空！");
        } else if (loginResult.equals(ResultMessage.Login_NoUser)) {
            // 不存在该用户
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setLabel("不存在对应用户！");
        } else if (loginResult.equals(ResultMessage.Login_ClerkSuccess)) {
            // 酒店工作人员登录成功
            rememberPW(rememberPWSelected, userID, password);
            stageController.loadStage("clerk/ClerkFrame.fxml", 1);
            stageController.closeStage(resource);
            ClerkFrameController controller = (ClerkFrameController) stageController.getController();
            controller.initial(userID);
        } else if (loginResult.equals(ResultMessage.Login_ManagerSuccess)) {
            // 网站管理人员登录成功
            rememberPW(rememberPWSelected, userID, password);
            stageController.loadStage("manager/ManagerMainView.fxml", 1);
            stageController.closeStage(resource);
            ManagerMainViewController managerMainViewController = (ManagerMainViewController) stageController.getController();
            managerMainViewController.init(userID);
        } else if (loginResult.equals(ResultMessage.Login_MarketerSuccess)) {
            // 网站营销人员登录成功
            rememberPW(rememberPWSelected, userID, password);
            stageController.loadStage("marketer/MarketerFrame.fxml", 1);
            stageController.closeStage(resource);
            MarketerFrameController marketerFrameController = (MarketerFrameController) stageController.getController();
            marketerFrameController.initial(userID);
        } else if (loginResult.equals(ResultMessage.Login_CustomerSuccess)) {
//        } else if (userID.equals("Admin") && password.equals("123456")) {
            // 客户登录成功
            rememberPW(rememberPWSelected, userID, password);
            stageController.loadStage("customer/CustomerMainView.fxml", 1);
            stageController.closeStage(resource);
            CustomerMainViewController customerMainViewController = (CustomerMainViewController) stageController.getController();
            customerMainViewController.init(userID);
        } else {
            // 密码错误
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setLabel("密码错误！");
            passwordField.setText("");//清空密码
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
     * 取消记住密码按钮结果，如果点击取消记住密码，执行取消记住密码操作
     */
    @FXML
    private void cancelRememberPW() throws RemoteException {
        if (!rememberPW.isSelected()) {
            //取消勾选记住密码
            logIn = new LogInController();
            logIn.cancelRemPassword(userNameField.getText());
        }
    }

    /**
     * 记住密码方法
     */
    private void rememberPW(boolean rememberPWSelected, String userID, String password) throws RemoteException {
        if (rememberPWSelected) {
            //如果选择了记住密码且登陆成功，则系统执行记住密码
            logIn = new LogInController();
            logIn.rememberPassword(userID, password);
        }
    }

    /**
     * 退出系统方法
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
