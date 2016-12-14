package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import ui.controller.CustomerSignUpController;
import ui.view.controllerservice.CustomerSignUp;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.MemberType;
import util.ResultMessage;
import vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/11/25.
 */
public class CustomerSignUpViewController implements ControlledStage{
    StageController stageController;

    private String resource = "customer/CustomerSignUpView.fxml";

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private Button upload;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField fisrtPasswordTextField;

    @FXML
    private TextField secondPasswordTextField;

    @FXML
    private ImageView iconImage;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        if (nameTextField.getText() != "" || emailTextField.getText() != ""
                || phoneTextField.getText() != "" || idTextField.getText() != ""
                || fisrtPasswordTextField.getText() != "" || secondPasswordTextField.getText() != ""){
            stageController = new StageController();
            stageController.loadStage("util/ConfirmExit.fxml", 0.75);
            ConfirmExitController controller = (ConfirmExitController) stageController.getController();
            controller.setToBeClosed(resource);
        }
        else{
            stageController = new StageController();
            stageController.closeStage(resource);
        }
    }

    @FXML
    private void uploadIcon(){

    }

    /**
     * 注册方法
     */
    @FXML
    private void signUp(){
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String ID = idTextField.getText();
        String firstPassword = fisrtPasswordTextField.getText();
        String secondPassword = secondPasswordTextField.getText();
        String picUrl = "";

        if(!firstPassword.equals(secondPassword)){
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setLabel("两次输入密码不一致！");
        }
        else{
            CustomerSignUp customerSignUp = new CustomerSignUpController();
            try{
                ResultMessage resultMessage = customerSignUp.signUp(new CustomerVO(name, firstPassword, phone, email, 500,  picUrl,  ID, MemberType.NONMEMBER));
                stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
                ErrorBoxController controller = (ErrorBoxController) stageController.getController();
                if(resultMessage == ResultMessage.WrongEmailFormat){
                    controller.setLabel("邮件格式错误！");
                }
                else if (resultMessage == ResultMessage.WrongPhoneFormat){
                    controller.setLabel("手机格式错误！");
                }
                else if (resultMessage == ResultMessage.Blank){
                    controller.setLabel("未填写完整信息！");
                }
                else if (resultMessage == ResultMessage.Customer_SignupExist){
                    controller.setLabel("该用户已存在！");
                }
                else if (resultMessage == ResultMessage.Customer_SignupSuccess){
                    controller.setLabel("注册成功！");
                    stageController = new StageController();
                    stageController.closeStage(resource);
                }
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }

}
