package ui.view.presentation.Login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import ui.controller.LogInController;
import ui.view.presentation.customer.CustomerMainView;
import util.ResultMessage;

/**
 * Created by ST on 2016/11/23.
 */
public class LoginViewController {
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

    private LoginView loginView;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public LoginViewController() {
    }

//    /**
//     * Initializes the controller class. This method is automatically called
//     * after the fxml file has been loaded.
//     */
//    @FXML
//    private void initialize() {
//
//    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param loginView
     */
    public void setLoginView(LoginView loginView) {

        this.loginView = loginView;

    }

    /**
     * Called when the user clicks on the login button.
     */
    @FXML
    private void handleLogin() {
        LogInController logInController = new LogInController();
        String password = passwordField.getText();
        String userID = userNameField.getText();
        ResultMessage result = logInController.login(userID, password);
        System.out.print(result);
    }

    @FXML
    private void handleRegister() {

    }

    @FXML
    private void handleSkip() {
        pane.getChildren().remove(pane);
        CustomerMainView customerMainView = new CustomerMainView();
    }
}
